package Dao

import Entity.User
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Query("select * from users")
    fun getAllUsers(): Flowable<List<User>>
}