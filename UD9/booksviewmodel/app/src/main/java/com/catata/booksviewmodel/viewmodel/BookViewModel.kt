package com.catata.booksviewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catata.booksviewmodel.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    // Los LiveData (estados) solo deben de poder cambiar desde el View Model, por ello se declaran private.
    // Para acceder al valor de los estados desde el exterior del View Model se crea una variable no mutable
    //  que almacenará el mismo valor que la variable privada

    // Lista de libros
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    // Libro seleccionado
    private val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book> = _selectedBook

    // Variable para indicar que se están obteniendo los datos del repositorio
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {  // Cuando se instancia un objeto BookViewModel tras llamar al constructor se ejecuta el bloque init
        loadBookList()
    }

    // Función para reutilizar este código en una futura mejora en la que se podrá recargar la lista de libros
    fun loadBookList() {
        // Corrutina: coroutineScope
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            delay(2000)
            _books.postValue(Book.getData())
            _isLoading.postValue(false)
        }
    }

    fun deleteBook(book: Book) {
        // Con API o BBDD se mandaría el id y se borraría de la BBDD
        // A continuación se actualiza la lista de libros eliminando el libro
        _books.value = _books.value?.filter { it != book }
    }

    // Al pulsar sobre un libro se almacena como seleccionado.
    fun onBookClicked(book: Book) {
        _selectedBook.value = book
    }

    // Para marcar/desmarcar el libro como favorito
    fun markAsFavorite(book: Book) {
        _books.value?.map {
            if (it == book) it.favorite = !it.favorite
        }
    }

    fun searchBook(searchString: String) {
        val searchList = mutableListOf<Book>()
        _books.value?.forEach {
//            val book = it.copy()
//            book.visible = book.title.contains(searchString, true)
//            searchList.add(book)

            // Estas líneas son similares a las comentadas
            val book = it.copy(visible = it.title.contains(searchString, true))
            searchList.add(book)
        }
        _books.value = searchList
    }

    fun resetSearchList() {
        val searchList = mutableListOf<Book>()
        _books.value?.forEach {
//            book.visible = true
//            searchList.add(book)

            // Estas líneas son similares a las comentadas
            val book = it.copy(visible = true)
            searchList.add(book)
        }
        _books.value = searchList
    }
}




