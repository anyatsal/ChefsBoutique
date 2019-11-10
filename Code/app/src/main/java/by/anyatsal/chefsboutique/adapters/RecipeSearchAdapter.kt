package by.anyatsal.chefsboutique.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.RecipeSearchItem
import coil.api.load
import coil.transform.CircleCropTransformation

class RecipeSearchAdapter(
    context: Context?,
    private val recipes: List<RecipeSearchItem>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecipeSearchAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_recipe_search,
                parent,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = recipes.size

    private fun getItem(position: Int): RecipeSearchItem = recipes[position]

    class ViewHolder(
        itemView: View,
        listener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.preview_recipe_img)
        private val name: TextView = itemView.findViewById(R.id.preview_recipe_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(recipe: RecipeSearchItem) {
            image.load(recipe.imageRes) {
                transformations(CircleCropTransformation())
            }
            name.text = recipe.name
        }
    }
}