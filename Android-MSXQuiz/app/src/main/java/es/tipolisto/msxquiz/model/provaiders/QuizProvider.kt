package es.tipolisto.msxquiz.model.provaiders

import es.tipolisto.msxquiz.model.Quiz


class QuizProvider {
    companion object{
        fun getQuizRandom(): Quiz {
            val position:Int= (0..7).random()
            return quizList.get(position)
        }
        fun getQuizListFull():List<Quiz>{
            return quizListFull
        }
        fun getQuizList():MutableList<Quiz>{
            return quizList.toMutableList()
        }

        fun setQuizList(quizListEntry:List<Quiz>){
            quizList=quizListEntry
        }
        private var quizList=emptyList<Quiz>()
        private val quizListFull = listOf<Quiz>(
            Quiz(
                id=0,
                question="Calcula el dato que debemos enviar al Puerto A8 para activar las 4 páginas de memoria RAM en el slot 3.",
                answer1 = "FF",
                answer2 = "FE",
                answer3 = "F0",
                correctAnswer = 1,
                image = 2,
                nameUser="Neo"
            ),
            Quiz(
                id=1,
                question="¿A qué tipo de programa corresponde este LEADER y cual es el nombre del programa?.\n" +
                        "DO DO DO DO DO DO DO DO DO DO 53 4F 46 54",
                answer1 = "Binario HOLA",
                answer2 = "ASCII SOFT",
                answer3 = "Binario SOFT",
                correctAnswer = 3,
                image = 2,
                nameUser="Trinity"
            ),
            Quiz(
                id=2,
                question="En vistas del siguiente LEADER: ¿Cuáles son las direcciones de Inicio, Fin y Ejecución del programa?\n"+
                        "00 88 00 C8 00 88",
                answer1 = "inicio 8000, fin C800, ejecución 8000",
                answer2 = "inicio c800, fin 8000, ejecución C800",
                answer3 = "inicio 8000, fin 8000, ejecución C800",
                correctAnswer = 1,
                image = 2,
                nameUser="Merovingio"
            ),
            Quiz(
                id=3,
                question="¿En basic que signo se utiliza para separar 2 instrucciones dentro de la misma línea?.",
                answer1 = "Espacio en blanco ( )",
                answer2 = "Punto y coma (;)",
                answer3 = "Dos puntos (:)",
                correctAnswer = 3,
                image = 2,
                nameUser="Morfeo"
            ),
            Quiz(
                id=4,
                question="Indica la lista que no contiene todos juegos de Konami",
                answer1 = "Sky jaguar, Break Shot, Salamander",
                answer2 = "Hyper Rally, Athletic Land, Golvellius ",
                answer3 = "Time Pilot, Mah-Jon, Knightmare ",
                correctAnswer = 2,
                image = 2,
                nameUser="Luna"
            ),
            Quiz(
                id=5,
                question="¿Puede un registro de 16 bits cargarse con un dato de 8 bits?.",
                answer1 = "No",
                answer2 = "Si pero se tendrá que inicialir el otro registro de 8 bits aparejado a 0",
                answer3 = "Si",
                correctAnswer = 2,
                image = 2,
                nameUser="Marte"
            ),
            Quiz(
                id=6,
                question="¿Qué significa LIFO?.",
                answer1 = "(List In - First Out): Primero en salir en la lista",
                answer2 = "(First Out - Last In): Primero en salir - Último en entrar",
                answer3 = "(Last In - First Out): Primero en entrar - Último en salir",
                correctAnswer = 3,
                image = 2,
                nameUser="Trinity"
            ),
            Quiz(
                id=7,
                question="¿Cual es el modo de acceso de una PILA?.",
                answer1 = "(Last In, First Out): último en entrar, primero en salir.",
                answer2 = "Se recarga",
                answer3 = "Se recuperan todos los datos",
                correctAnswer = 1,
                image = 2,
                nameUser="Merovingio"
            ),
            Quiz(
                id=8,
                question="¿Quien es el programador del juego Ghost park?.",
                answer1 = "Juan Miralles",
                answer2 = "Juan Morales",
                answer3 = "Tadeo Jones",
                correctAnswer = 2,
                image = 2,
                nameUser="Mantra"
            ),
            Quiz(
                id=9,
                question="¿Quien compuso la música del juego Tamari?.",
                answer1 = "Misimo Nagazaki",
                answer2 = "Toni Galvez",
                answer3 = "Gabriel Caffarena",
                correctAnswer = 1,
                image = 2,
                nameUser="Sin sentido name"
            ),
            Quiz(
                id=10,
                question="¿Que es el formato TSX?.",
                answer1 = "Contiene datos comprimidos con pletter",
                answer2 = "Es un superconjunto de tzx 1.20 definition, son mejores que los .cas y conserva los archivos como los originales",
                answer3 = "Representan imágenes de laser discs",
                correctAnswer = 2,
                image = 2,
                nameUser="Desconocido"
            )
        )

    }
}