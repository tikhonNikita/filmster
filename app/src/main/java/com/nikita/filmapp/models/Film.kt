package com.nikita.filmapp.models

import com.google.gson.Gson
import com.nikita.filmapp.R
import kotlinx.serialization.Serializable

val datus = """
    {
            "adult": false,
            "backdrop_path": "/pfAZP7JvTTxqgq7n6A1OYgkAdEW.jpg",
            "id": 894205,
            "title": "Werewolf by Night",
            "original_language": "en",
            "original_title": "Werewolf by Night",
            "overview": "On a dark and somber night, a secret cabal of monster hunters emerge from the shadows and gather at the foreboding Bloodstone Temple following the death of their leader. In a strange and macabre memorial to the leader’s life, the attendees are thrust into a mysterious and deadly competition for a powerful relic—a hunt that will ultimately bring them face to face with a dangerous monster.",
            "poster_path": "/1n2q0Y1pX8PkQh9imqGbNH7Bw4q.jpg",
            "media_type": "movie",
            "genre_ids": [
                28,
                14,
                27
            ],
            "popularity": 510.713,
            "release_date": "2022-09-25",
            "video": false,
            "vote_average": 7.4,
            "vote_count": 331
        }
"""


@Serializable
data class Film(
    val id: Int,
    val title: String,
    val image: Int,
    val description: String,
    var selected: Boolean
)


val movieT = Gson().fromJson(datus, Movie::class.java)

val filmLists = listOf(
    Film(1, movieT.title, R.drawable.batman, "Film about batman", false),
    Film(
        2,
        "Iron man 3",
        R.drawable.iron_man,
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\n" +
                "Why do we use it?\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                "\n" +
                "\n" +
                "Where does it come from?\n" +
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
        false
    ),
    Film(
        3,
        "Doctor Strange in the Multiverse of Madness",
        R.drawable.multiverse_of_madness,
        "Doctor Strange in the Multiverse of Madness film",
        false
    ),
)

