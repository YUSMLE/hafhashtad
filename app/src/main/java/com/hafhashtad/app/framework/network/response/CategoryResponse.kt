import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("color")
    val color: String
)
