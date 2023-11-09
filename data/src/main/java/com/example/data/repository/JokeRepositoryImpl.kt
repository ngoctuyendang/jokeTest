package com.example.data.repository

import com.example.data.database.JokeDao
import com.example.data.entities.mapListJokeToJokeDto
import com.example.domain.models.JokeItem
import com.example.domain.repository.JokeRepository
import io.reactivex.Single

class JokeRepositoryImpl(
    private val jokeDao: JokeDao
) : JokeRepository {
    private val listData: ArrayList<JokeItem> = arrayListOf(
        JokeItem(
            id = 1,
            content = "A child asked his father, \"How were people born?\" So his father said," +
                    " \"Adam and Eve made babies, then their babies became adults " +
                    "and made babies, and so on.\"\n" + "\n" +
                    "The child then went to his mother, asked her the same question " +
                    "and she told him, \"We were monkeys then we evolved to become " +
                    "like we are now.\"\n" + "\n" +
                    "The child ran back to his father and said, " +
                    "\"You lied to me!\" His father replied," +
                    " \"No, your mom was talking about her side of the family.\"",
            funnyStatus = -1
        ),
        JokeItem(
            id = 2,
            content = """
                Teacher: "Kids,what does the chicken give you?" 
                Student: "Meat!" 
                Teacher: "Very good! Now what does the pig give you?" 
                Student: "Bacon!" 
                Teacher: "Great! And what does the fat cow give you?" 
                Student: "Homework!"
            """.trimIndent(),
            funnyStatus = -1
        ),
        JokeItem(
            id = 3,
            content = "The teacher asked Jimmy, \"Why is your cat at school today Jimmy?\" " +
                    "Jimmy replied crying, \"Because I heard my daddy tell my mommy, " +
                    "'I am going to eat that pussy once Jimmy leaves for school today!'\"",
            funnyStatus = -1
        ),
        JokeItem(
            id = 4,
            content = "A housewife, an accountant and a lawyer were asked \"How much is 2+2?\"" +
                    " The housewife replies: \"Four!\". The accountant says: \"I think " +
                    "it's either 3 or 4. Let me run those figures through " +
                    "my spreadsheet one more time.\" The lawyer pulls the drapes, " +
                    "dims the lights and asks in a hushed voice, " +
                    "\"How much do you want it to be?\"",
            funnyStatus = -1
        )
    )

    override fun getListJoke(): Single<ArrayList<JokeItem>> {
        return Single.just(listData)
    }

    override fun voteForJoke(jokeId: Int, isFunny: Boolean) =
        jokeDao.updateFunnyStatus(jokeId, if (isFunny) 1 else 0)

    override fun addListJokeToDb(listJoke: List<JokeItem>) =
        jokeDao.addAllJoke(listData.mapListJokeToJokeDto())

}