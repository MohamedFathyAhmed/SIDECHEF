package com.example.sidechef.HomeActivity.View.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sidechef.HomeActivity.View.ui.DetailViewinterface;
import com.example.sidechef.R;
import com.example.sidechef.databinding.FragmentHomeBinding;
import com.example.sidechef.model.data.models.Meal;

public class HomeFragment extends Fragment {
Button btn;
    Meal meal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.btngo);


                btn.setOnClickListener(v->{
                     meal =new Meal( "52842", "Broccoli & Stilton soup",
                            null, "Starter", "British", "Heat the rapeseed oil in a large saucepan and then add the onions. Cook on a medium heat until soft. Add a splash of water if the onions start to catch. Add the celery, leek, potato and a knob of butter. Stir until melted, then cover with a lid. Allow to sweat for 5 minutes. Remove the lid. Pour in the stock and add any chunky bits of broccoli stalk. Cook for 10 – 15 minutes until all the vegetables are soft. Add the rest of the broccoli and cook for a further 5 minutes. Carefully transfer to a blender and blitz until smooth. Stir in the stilton, allowing a few lumps to remain. Season with black pepper and serve.", "https://www.themealdb.com/images/media/meals/tvvxpv1511191952.jpg", null, "https://www.youtube.com/watch?v=_HgVLpmNxTY", "Rapeseed Oil", "Onion", "Celery", "Leek", "Potatoes", "Butter", "Vegetable Stock", "Broccoli",
                            "Stilton Cheese", "", "", "", "", "", "", "", "", "", "", "", "2 tblsp ", "1 finely chopped ", "1", "1 sliced", "1 medium", "1 knob", "1 litre hot", "1 Head chopped", "140g", "", "", "",
                            "", "", "", "", "", "", "", "",
                            "https://www.bbcgoodfood.com/recipes/1940679/broccoli-and-stilton-soup", null, null,null);

                    HomeFragmentDirections.ActionNavigationHomeToDetailFragment action =  HomeFragmentDirections.actionNavigationHomeToDetailFragment();
                    action.setDataMeal(meal);
                    Log.i("TAG", "onCreateView: "+meal.getStrArea());
                    Navigation.findNavController(v).navigate(action);
                });


    }
}