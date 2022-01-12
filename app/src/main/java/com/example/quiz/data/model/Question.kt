import com.google.gson.annotations.SerializedName
import kotlinx.serialization.json.*



data class Question(
    val id: Long,
    val question: String,
    val description: String,
    val answers: Answers,

    @SerializedName("multiple_correct_answers")
    val multipleCorrectAnswers: String,

    @SerializedName("correct_answers")
    val correctAnswers: CorrectAnswers,

    val explanation: String,
    val tip: String? = null,
    val tags: JsonArray,
    val category: String,
    val difficulty: String
)


data class Answers(
    @SerializedName("answer_a")
    val answerA: String,

    @SerializedName("answer_b")
    val answerB: String,

    @SerializedName("answer_c")
    val answerC: String,

    @SerializedName("answer_d")
    val answerD: String,

    @SerializedName("answer_e")
    val answerE: String? = null,

    @SerializedName("answer_f")
    val answerF: String? = null
)

data class CorrectAnswers(
    @SerializedName("answer_a_correct")
    val answerACorrect: String,

    @SerializedName("answer_b_correct")
    val answerBCorrect: String,

    @SerializedName("answer_c_correct")
    val answerCCorrect: String,

    @SerializedName("answer_d_correct")
    val answerDCorrect: String,

    @SerializedName("answer_e_correct")
    val answerECorrect: String,

    @SerializedName("answer_f_correct")
    val answerFCorrect: String
)
