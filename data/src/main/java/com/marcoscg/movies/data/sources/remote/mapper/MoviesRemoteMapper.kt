package com.marcoscg.movies.data.sources.remote.mapper

import com.marcoscg.movies.data.sources.remote.model.RemoteDataUserResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteDeleteFavouriteResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteFavouriteResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteLoginResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteMovie
import com.marcoscg.movies.data.sources.remote.model.RemoteMovieDetail
import com.marcoscg.movies.data.sources.remote.model.RemoteMoviesResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteRegisterResponse
import com.marcoscg.movies.model.*
import com.marcoscg.movies.model.utils.orDefault
import com.marcoscg.movies.model.utils.orFalse

class MoviesRemoteMapper {

    fun mapFromRemote(remoteMoviesResponse: RemoteMoviesResponse): MoviesResponse {
        return MoviesResponse(remoteMoviesResponse.page, remoteMoviesResponse.total_results,
            remoteMoviesResponse.total_pages, remoteMoviesResponse.results.map { remoteMovie ->
                Movie(
                    remoteMovie.popularity.orDefault(),
                    remoteMovie.vote_count.orDefault(),
                    remoteMovie.video.orFalse(),
                    remoteMovie.poster_path.orEmpty(),
                    remoteMovie.id,
                    remoteMovie.adult.orFalse(),
                    remoteMovie.backdrop_path.orEmpty(),
                    remoteMovie.original_language.orEmpty(),
                    remoteMovie.original_title.orEmpty(),
                    remoteMovie.title,
                    remoteMovie.vote_average.orDefault(),
                    remoteMovie.overview.orEmpty(),
                    remoteMovie.release_date.orEmpty()
                )
            })
    }

    fun mapDetailFromRemote(remoteMovieDetail: RemoteMovieDetail): MovieDetail {
        return MovieDetail(
            remoteMovieDetail.adult.orFalse(),
            remoteMovieDetail.backdrop_path.orEmpty(),
            remoteMovieDetail.budget.orDefault(),
            remoteMovieDetail.genres.orEmpty().map {
                Genres(it.id, it.name.orEmpty())
            },
            remoteMovieDetail.homepage.orEmpty(),
            remoteMovieDetail.id,
            remoteMovieDetail.imdb_id.orEmpty(),
            remoteMovieDetail.original_language.orEmpty(),
            remoteMovieDetail.original_title.orEmpty(),
            remoteMovieDetail.overview.orEmpty(),
            remoteMovieDetail.popularity.orDefault(),
            remoteMovieDetail.poster_path.orEmpty(),
            remoteMovieDetail.production_companies.orEmpty().map {
                ProductionCompanies(
                    it.id,
                    it.logo_path.orEmpty(),
                    it.name.orEmpty(),
                    it.origin_country.orEmpty()
                )
            },
            remoteMovieDetail.release_date.orEmpty(),
            remoteMovieDetail.revenue.orDefault(),
            remoteMovieDetail.runtime.orDefault(),
            remoteMovieDetail.status.orEmpty(),
            remoteMovieDetail.tagline.orEmpty(),
            remoteMovieDetail.title,
            remoteMovieDetail.video.orFalse(),
            remoteMovieDetail.vote_average.orDefault(),
            remoteMovieDetail.vote_count.orDefault()
        )
    }

    fun mapRegisterFromRemote(remoteUserRegister: RemoteRegisterResponse): RegisterStatus {
        return RegisterStatus(
            remoteUserRegister.title.orEmpty(),
            remoteUserRegister.message.orEmpty()
        )
    }

    fun mapLoginFromRemote(remoteLoginResponse: RemoteLoginResponse): LoginResponse {
        return LoginResponse(remoteLoginResponse.accessToken.orEmpty())
    }

    fun mapDataUserFromRemote(remoteDataUserResponse: RemoteDataUserResponse): DataUserResponse {
        return DataUserResponse(
            remoteDataUserResponse.username,
            remoteDataUserResponse.email,
            remoteDataUserResponse.id
        )
    }
    fun mapFavouriteFromRemote(remoteFavouriteResponse: RemoteFavouriteResponse): FavouriteResponse {
        return FavouriteResponse(
            remoteFavouriteResponse.movieId
        )
    }
    fun mapFavouritesFromRemote(remoteMovies: List<RemoteMovie>): List<Movie> {
        return remoteMovies.map {
            Movie(
                it.popularity.orDefault(),
                it.vote_count.orDefault(),
                it.video.orFalse(),
                it.poster_path.orEmpty(),
                it.id,
                it.adult.orFalse(),
                it.backdrop_path.orEmpty(),
                it.original_language.orEmpty(),
                it.original_title.orEmpty(),
                it.title,
                it.vote_average.orDefault(),
                it.overview.orEmpty(),
                it.release_date.orEmpty()
            )
        }
    }
    fun mapDeleteFavouritesFromRemote(emoteDeleteFavouriteResponse: RemoteDeleteFavouriteResponse): DeleteFavouriteResponse {
        return DeleteFavouriteResponse(
            emoteDeleteFavouriteResponse.message
        )
    }

}