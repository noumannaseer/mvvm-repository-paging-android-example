package com.elad.themovie.service.repository.storge.paging;


import com.elad.themovie.service.repository.storge.MovieDao;

import androidx.paging.DataSource;

//*****************************************************
public class DBMoviesDataSourceFactory extends DataSource.Factory
//*****************************************************
{
    private static final String TAG = DBMoviesDataSourceFactory.class.getSimpleName();
    private DBMoviesPageKeyedDataSource moviesPageKeyedDataSource;

    //*****************************************************
    public DBMoviesDataSourceFactory(MovieDao dao)
    //*****************************************************
    {
        moviesPageKeyedDataSource = new DBMoviesPageKeyedDataSource(dao);
    }

    //*****************************************************
    @Override
    public DataSource create()
    //*****************************************************
    {
        return moviesPageKeyedDataSource;
    }

}
