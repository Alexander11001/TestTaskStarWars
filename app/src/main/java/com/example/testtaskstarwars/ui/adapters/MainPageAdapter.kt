package com.example.testtaskstarwars.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskstarwars.R
import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.models.Planet
import com.example.testtaskstarwars.domain.models.Starships
import kotlinx.coroutines.launch

class MainPageAdapter(
    private val callback: FavoriteItemCallback,
    private val scope: LifecycleCoroutineScope
) :
    ListAdapter<MainPageItem, RecyclerView.ViewHolder>(MainPageDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MainPageItem.PeopleItem -> R.layout.people_item
            is MainPageItem.PlanetItem -> R.layout.planet_item
            is MainPageItem.StarshipsItem -> R.layout.starships_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = when (viewType) {
            R.layout.people_item -> R.layout.people_item
            R.layout.starships_item -> R.layout.starships_item
            R.layout.planet_item -> R.layout.planet_item
            else -> throw IllegalArgumentException("Invalid viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return when (viewType) {
            R.layout.people_item -> PeopleViewHolder(view)
            R.layout.starships_item -> StarshipsViewHolder(view)
            R.layout.planet_item -> PlanetViewHolder(view)

            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is MainPageItem.PeopleItem -> (holder as PeopleViewHolder).bind(item.people)
            is MainPageItem.PlanetItem -> (holder as PlanetViewHolder).bind(item.planet)
            is MainPageItem.StarshipsItem -> (holder as StarshipsViewHolder).bind(item.starships)
        }
    }

    inner class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(people: People) {
            val name = itemView.findViewById<TextView>(R.id.nameTextView)
            val gender = itemView.findViewById<TextView>(R.id.genderTextView)
            val starships = itemView.findViewById<TextView>(R.id.starshipsTextView)
            val isFavoriteButton = itemView.findViewById<ImageButton>(R.id.peopleFavoriteButton)

            name.text = people.name
            gender.text = people.gender
            starships.text = people.starships.toString()

            scope.launch {
                callback.isItemFavorite(MainPageItem.PeopleItem(people))
                    .collect { isFavorite ->
                        people.isFavorite = isFavorite
                        isFavoriteButton.setImageResource(
                            if (isFavorite) R.drawable.heart_red_filled
                            else R.drawable.heart_empty
                        )
                    }
            }

            isFavoriteButton.setOnClickListener {
                callback.onFavoriteChanged(MainPageItem.PeopleItem(people))
            }
        }
    }

    inner class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(planet: Planet) {
            val name = itemView.findViewById<TextView>(R.id.namePlanetTextView)
            val diameter = itemView.findViewById<TextView>(R.id.diameterTextView)
            val population = itemView.findViewById<TextView>(R.id.populationTextView)
            val isFavoriteButton = itemView.findViewById<ImageButton>(R.id.planetFavoriteButton)

            name.text = planet.name
            diameter.text = planet.diameter
            population.text = planet.population

            scope.launch {
                callback.isItemFavorite(MainPageItem.PlanetItem(planet))
                    .collect { isFavorite ->
                        planet.isFavorite = isFavorite
                        isFavoriteButton.setImageResource(
                            if (isFavorite) R.drawable.heart_red_filled
                            else R.drawable.heart_empty
                        )
                    }
            }

            isFavoriteButton.setOnClickListener {
                callback.onFavoriteChanged(MainPageItem.PlanetItem(planet))
            }
        }
    }


    inner class StarshipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(starships: Starships) {
            val name = itemView.findViewById<TextView>(R.id.nameStarshipTextView)
            val model = itemView.findViewById<TextView>(R.id.modelTextView)
            val manufacturer = itemView.findViewById<TextView>(R.id.manufacturerTextView)
            val passengers = itemView.findViewById<TextView>(R.id.passengersTextView)
            val isFavoriteButton = itemView.findViewById<ImageButton>(R.id.starshipFavoriteButton)

            name.text = starships.name
            model.text = starships.model
            manufacturer.text = starships.manufacturer
            passengers.text = starships.passengers

            scope.launch {
                callback.isItemFavorite(MainPageItem.StarshipsItem(starships))
                    .collect { isFavorite ->
                        starships.isFavorite = isFavorite
                        isFavoriteButton.setImageResource(
                            if (isFavorite) R.drawable.heart_red_filled
                            else R.drawable.heart_empty
                        )
                    }
            }

            isFavoriteButton.setOnClickListener {
                callback.onFavoriteChanged(MainPageItem.StarshipsItem(starships))
            }
        }
    }

    class MainPageDiffCallback : DiffUtil.ItemCallback<MainPageItem>() {
        override fun areItemsTheSame(oldItem: MainPageItem, newItem: MainPageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MainPageItem, newItem: MainPageItem): Boolean {
            return oldItem == newItem
        }
    }
}

sealed class MainPageItem {
    //the assumption is made that names cannot be repeated
    abstract val id: String

    data class PeopleItem(val people: People) : MainPageItem() {
        override val id = people.name
    }

    data class PlanetItem(val planet: Planet) : MainPageItem() {
        override val id = planet.name
    }

    data class StarshipsItem(val starships: Starships) : MainPageItem() {
        override val id = starships.name
    }
}