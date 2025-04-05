package com.jchunga.movieapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jchunga.movieapp.core.utils.Constants
import com.jchunga.movieapp.data.models.MoviesResponse
import com.jchunga.movieapp.domain.entities.Movie

class MoviesPagingSource(
    private val movieApi: MovieApi,
    private val language: String,
    private val movieGetType: Int
) : PagingSource<Int, Movie>(){

    private var totalMoviesCount = 0

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let{ anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {

            val movieResponse = when (movieGetType) {
                1 -> {
                    movieApi.getMovies(authToken = Constants.API_KEY, language = language, page = page)
                }
                2 -> {
                    movieApi.getUpcomingMovies(authToken = Constants.API_KEY, language = language, page = page)
                }
                else -> {
                    movieApi.getPopularMovies(authToken = Constants.API_KEY, language = language, page = page)
                }
            }

            totalMoviesCount += movieResponse.movies.size
            val movies = movieResponse.movies.distinctBy {  it.id }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty() || totalMoviesCount >= movieResponse.totalResults) null else page + 1
            )
        } catch ( e:Exception ){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}