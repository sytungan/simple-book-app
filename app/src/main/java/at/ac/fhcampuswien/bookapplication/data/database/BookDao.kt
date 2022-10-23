package at.ac.fhcampuswien.bookapplication.data.database

import androidx.room.*
import at.ac.fhcampuswien.bookapplication.models.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE id IN (:bookIds)")
    fun loadAllByIds(bookIds: IntArray): List<Book>

    @Query("SELECT * FROM book WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Book

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg book: Book)

    @Query("DELETE FROM book WHERE id IN (:bookIds)")
    fun deleteByIds(bookIds: IntArray)

    @Delete
    fun delete(book: Book)
}