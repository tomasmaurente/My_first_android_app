package com.example.data.local_data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local_data_base.dao.ParkingDao
import com.example.data.local_data_base.entities.LotRoom

@Database(version = 1, entities = [LotRoom::class])
abstract class LotDataBase : RoomDatabase() {
    companion object{

        private const val DATABASE_NAME = "lotDataBase"
        private lateinit var instance: LotDataBase

        @Synchronized
        fun getInstance( context: Context): LotDataBase{
            if(!this::instance.isInitialized) {
                instance = Room.databaseBuilder(context.applicationContext,
                                                LotDataBase::class.java,
                                                DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
    abstract fun characterDao(): ParkingDao
}