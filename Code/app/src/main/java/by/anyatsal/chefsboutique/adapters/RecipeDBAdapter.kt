package by.anyatsal.chefsboutique.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.RecipeDBItem

class RecipeDBAdapter(
    context: Context?,
    private val recipes: List<RecipeDBItem>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecipeDBAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.item_recipe,
                parent,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = recipes.size

    private fun getItem(position: Int): RecipeDBItem = recipes[position]

    class ViewHolder(
        itemView: View,
        listener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.preview_recipe_img)
        private val name: TextView = itemView.findViewById(R.id.preview_recipe_name)
        private val description: TextView = itemView.findViewById(R.id.preview_recipe_description)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(recipe: RecipeDBItem) {
            name.text = recipe.name
            description.text = recipe.description
            //image
        }
    }
}