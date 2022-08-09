package com.example.mvvmtest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtest.R
import com.example.mvvmtest.databinding.FragmentCocktailDetailsBinding
import com.example.mvvmtest.databinding.FragmentCocktailResultBinding
import com.example.mvvmtest.databinding.LayoutIngredientsBinding
import com.example.mvvmtest.model.CocktailDetails
import com.example.mvvmtest.model.CocktailSearch
import com.example.mvvmtest.view.adapter.CocktailAdapter
import com.example.mvvmtest.viewmodel.TAG
import com.example.mvvmtest.viewmodel.displayCocktailDetails
import com.squareup.picasso.Picasso

class CocktailDetailsFragment: Fragment() {


    companion object {
        const val DETAILS_ID_DRINK = " DETAILS_ID_DRINK"
        fun newInstance(idDrink: String) =
            CocktailDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DETAILS_ID_DRINK, idDrink)
                }
            }
    }

    private lateinit var binding: FragmentCocktailDetailsBinding
    private lateinit var mergeBinding: LayoutIngredientsBinding
    private val viewModel: CocktailViewModel by lazy {
        ViewModelProvider(this)[CocktailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCocktailDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        mergeBinding = LayoutIngredientsBinding.bind(binding.root)
        arguments?.getString(DETAILS_ID_DRINK)?.let {
            getCocktailDetails(it)
        }
        initObservables()
        return binding.root
    }

    private fun initObservables() {
        viewModel.cocktailDetails.observe(viewLifecycleOwner) {
            updateView(it)
        }


    }

    private fun updateView(data: CocktailDetails) {

        Log.d(TAG, "updateView $data")
        mergeBinding.ingredientOne.text = data.drinks[0].strIngredient1
        mergeBinding.ingredientTwo.text = data.drinks[0].strIngredient3
        mergeBinding.ingredientThree.text = data.drinks[0].strIngredient3
        mergeBinding.ingredientFour.text = data.drinks[0].strIngredient4
        binding.cocktailNameDetails.text = data.drinks[0].strDrink
        Picasso.get()
            .load(data.drinks[0].strDrinkThumb)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.cocktailImageDetails
        )
    }


    private fun getCocktailDetails(idDrink: String) {
        viewModel.getCocktailDetails(idDrink)

    }
}
    
    //todo create binding for this fragement 
    //todo fragmentCocktailDetailsBinding
    // todo ovveride onCreateView to init binding
    // TODO: in the onCreateView get the arguments  
    // TODO: from the arguements invoke viewModel.getCocktailDetails
    // todo initObservables and viewModel.cocktailDetials.osberve...
    // TODO: display the data binding.tv.....
