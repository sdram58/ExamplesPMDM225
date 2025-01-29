package com.catata.bookviewmodelflows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catata.bookviewmodelflows.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    // En este caso usamos un Flow en lugar de un Live Data.

    // Lista de libros
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    // Libro seleccionado
    private val _selectedBook = MutableStateFlow<Book>(Book())
    val selectedBook: StateFlow<Book> = _selectedBook

    // Variable para indicar que se están obteniendo los datos del repositorio
    private var _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {  // Cuando se instancia un objeto BookViewModel tras llamar al constructor se ejecuta el bloque init
        loadBookList()
    }

    // Función para reutilizar este código en una futura mejora en la que se podrá recargar la lista de libros
    fun loadBookList() {
        // Corrutina: coroutineScope
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            delay(2000)
            _books.value = Book.getData()
            _isLoading.value = false
        }
    }

    fun deleteBook(book: Book) {
        // Con API o BBDD se mandaría el id y se borraría de la BBDD
        // A continuación se actualiza la lista de libros eliminando el libro
        _books.value = _books.value.filter { it != book }
    }

    // Al pulsar sobre un libro se almacena como seleccionado.
    fun onBookClicked(book: Book) {
        _selectedBook.value = book
    }

    // Para marcar/desmarcar el libro como favorito
    fun markAsFavorite() {


        //Actualizamos con una nueva lista, para que cambia el valor del contenido
        // del stateFlow, recuerda que el contenido es una referencia a una lista
        _books.update { books -> //Actualizamos la lista para que se recomponga MainScreen
            books.map { book ->
                if (book == _selectedBook.value){
                    val favorite = !book.favorite
                    val b = book.copy(favorite = favorite)
                    _selectedBook.update {//Actualizamos el actual para que se recomponga BookInfoScreen
                        b
                    }
                    b
                } // Nueva instancia de Book
                else book
            }
        }
    }

    fun searchBook(searchString: String) {
        val searchList = mutableListOf<Book>()
        _books.value.forEach {
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




