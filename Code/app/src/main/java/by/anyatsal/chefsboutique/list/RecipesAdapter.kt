package by.anyatsal.chefsboutique.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.Recipe

class RecipesAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recipes = emptyList<Recipe>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.preview_recipe_name)
        val description: TextView = itemView.findViewById(R.id.preview_recipe_description)
        val category: TextView = itemView.findViewById(R.id.preview_recipe_category)
        val image: ImageView = itemView.findViewById(R.id.preview_recipe_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = recipes[position]
        holder.name.text = current.name
        holder.description.text = current.description
        holder.category.text = current.category
        //holder.image.setImageResource()
    }

    internal fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun getItemCount() = recipes.size
}