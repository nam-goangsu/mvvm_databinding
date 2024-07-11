package com.example.test_room.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_room.room.bo.Userdata
import com.example.test_room.room.dao.UserDao


@Database(
    entities = [Userdata::class/*, CountGas::class, afterpart::class*/],
    version = 1/*,
    exportSchema = false*/
)
//@TypeConverters(Converters::class)
abstract class DefaltDatabase : RoomDatabase() {
    abstract fun userdao(): UserDao
//    abstract fun gasinfdao(): Dao_gasinfo
//    abstract fun afterpart(): afterpart

    companion object {

        @Volatile
        private var INSTANCE: DefaltDatabase? = null

        fun getDataabase(context: Context/*,scope: CoroutineScope*/):DefaltDatabase{
            Log.d("database " , "instance ${INSTANCE}")
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                Log.d("database " , "instance NOT NULL ${INSTANCE}")
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DefaltDatabase::class.java,
                    "defaltdb.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                 //   .addCallback(defaltDatabaseCallback(scope))

                    .build()
                INSTANCE = instance
                // return instance
                Log.d("database " , "instance MAKE IT ${INSTANCE}")
                return instance
            }
        }

/*private class defaltDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { defaltDatabase ->
            scope.launch(Dispatchers.IO) {
                defaltDatabase(defaltDatabase.userdao())
            }
        }
    }
}
suspend fun defaltDatabase(defaltDefaltdao: UserDao){
    defaltDefaltdao.del_defaultuser("test")
}*/
        /*@OptIn(InternalCoroutinesApi::class)
        @Synchronized
        fun getDefaltDataBase(context: Context): DefaltDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, DefaltDatabase::class.java, "item_database")
                    .build()
                    .also { INSTANCE = it }
            }
        }*/

        /*
                @OptIn(InternalCoroutinesApi::class)
                fun getDefaltDataBase(context: Context):DefaltDatabase{
                    val tempInstance = INSTANCE
                    if(tempInstance != null){
                        return tempInstance
                    }
                    synchronized(this){ //synchronized는 새로운 데이터베이스를 instance시킵니다.
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            DefaltDatabase::class.java,
                            "user_database.db"
                        ).build()
                        INSTANCE = instance
                        return instance
                    }
                }*/
/*
        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context) =
            INSTANCE ?: synchronized(LOCK) { //database가 instance될때 invoke가 실행됩니다.
                INSTANCE ?: createDatabase(context).also { //만약 인스턴스가없으면 synchronized안에있는 코드를 실행합니다.
                    INSTANCE = it
                }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DefaltDatabase::class.java, //Database class를 연결해줍니다.
                "user_database.db" //article db의 이름을 정합니다.
            ).build()
*/

    }
}