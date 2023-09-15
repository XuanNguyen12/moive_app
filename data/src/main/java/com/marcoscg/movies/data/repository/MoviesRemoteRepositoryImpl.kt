package com.marcoscg.movies.data.repository

import com.marcoscg.movies.data.sources.remote.api.ApiClient
import com.marcoscg.movies.data.sources.remote.mapper.MoviesRemoteMapper
import com.marcoscg.movies.data.sources.remote.model.MovieIdDto
import com.marcoscg.movies.data.sources.remote.model.RemotePostComment
import com.marcoscg.movies.data.sources.remote.model.RemoteSearchMovies
import com.marcoscg.movies.data.sources.remote.model.RemoteUserLogin
import com.marcoscg.movies.data.sources.remote.model.RemoteUserRegister
import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.CheckFavouriteResponse
import com.marcoscg.movies.model.DataUserResponse
import com.marcoscg.movies.model.DeleteFavouriteResponse
import com.marcoscg.movies.model.FavouriteResponse
import com.marcoscg.movies.model.GetCommentResponse
import com.marcoscg.movies.model.LoginResponse
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.MovieDetail
import com.marcoscg.movies.model.MoviesResponse
import com.marcoscg.movies.model.PostComment
import com.marcoscg.movies.model.PostCommentResponse
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.model.SearchMovies
import com.marcoscg.movies.model.UserLogin
import com.marcoscg.movies.model.UserRegister
import io.reactivex.Single

class MoviesRemoteRepositoryImpl(private val moviesRemoteMapper: MoviesRemoteMapper) :
    MoviesRemoteRepository {

    override fun getPopularMovies(page: Int): Single<MoviesResponse> {
        return ApiClient.movieService().getPopularMovies(page).map {
            moviesRemoteMapper.mapFromRemote(it)
        }
    }

    override fun getUpcomingMovies(page: Int): Single<MoviesResponse> {
        return ApiClient.movieService().getUpcomingMovies(page).map {
            moviesRemoteMapper.mapFromRemote(it)
        }
    }

    override fun getSingleMovie(id: String): Single<MovieDetail> {
        return ApiClient.movieService().getSingleMovie(id).map {
            moviesRemoteMapper.mapDetailFromRemote(it)
        }
    }

    override fun registerUser(userRegister: UserRegister): Single<RegisterStatus> {

        return ApiClient.movieService().registerUser(
            RemoteUserRegister(
                userRegister.username,
                userRegister.email,
                userRegister.password
            )
        ).map {
            moviesRemoteMapper.mapRegisterFromRemote(it)
        }
    }

    override fun loginUser(userLogin: UserLogin): Single<LoginResponse> {
        return ApiClient.movieService()
            .loginUser(RemoteUserLogin(userLogin.email, userLogin.password)).map {
                moviesRemoteMapper.mapLoginFromRemote(it)
            }
    }

    override fun getDataUser(): Single<DataUserResponse> {
        return ApiClient.movieService()
            .getDataUser().map {
                moviesRemoteMapper.mapDataUserFromRemote(it)
            }
    }

    override fun addFavourite(id: String): Single<FavouriteResponse> {
        return ApiClient.movieService()
            .setFavourite(MovieIdDto(id) ).map {
                moviesRemoteMapper.mapFavouriteFromRemote(it)
            }
    }

    override fun getFavourite(): Single<List<Movie>> {
        return ApiClient.movieService().getFavourite().map {
            moviesRemoteMapper.mapFavouritesFromRemote(it)
        }
    }

    override fun checkFavourite(id : String): Single<CheckFavouriteResponse> {
        return ApiClient.movieService().checkFavourite(id).map {
            moviesRemoteMapper.mapCheckFavouriteFromRemote(it)
        }
    }

    override fun searchMovie(page: Int, searchMovies: SearchMovies): Single<MoviesResponse> {
        return ApiClient.movieService().searchMovie(searchMovies.query ?: "", page ).map {
            moviesRemoteMapper.mapFromRemote(it)
        }
    }


    override fun deleteFavourite(id: String): Single<DeleteFavouriteResponse> {
        return ApiClient.movieService().deleteFavourite(MovieIdDto(id)).map {
            moviesRemoteMapper.mapDeleteFavouritesFromRemote(it)
        }
    }


    override fun getCommentMovie(
        id : String
    ): Single<List<GetCommentResponse>> {
        return ApiClient.movieService().getCommentMovie(id).map {
            moviesRemoteMapper.mapGetCommentFromRemote(it)
        }
    }
    override fun postCommentMovie(
        postComment : PostComment
    ): Single<PostCommentResponse> {
        return ApiClient.movieService().postCommentMovie(RemotePostComment(postComment.movieId, postComment.content) ).map {
            moviesRemoteMapper.mapPostCommentFromRemote(it)
        }
    }

}