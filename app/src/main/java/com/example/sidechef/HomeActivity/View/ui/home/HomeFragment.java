package com.example.sidechef.HomeActivity.View.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.R;
import com.example.sidechef.model.data.models.Category;
import com.example.sidechef.model.data.models.Meal;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sidechef.HomeActivity.View.ui.DetailViewinterface;
import com.example.sidechef.R;
import java.util.Arrays;
import java.util.List;
import com.example.sidechef.model.data.models.Meal;

public class HomeFragment extends Fragment {
        RecyclerView categoryrecyclerView;
        RecyclerView randomrecyclerView;
        Button btn;
        Meal meal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        private FragmentHomeBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                        ViewGroup container, Bundle savedInstanceState) {
                HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

                binding = FragmentHomeBinding.inflate(inflater, container, false);
                View root = binding.getRoot();

        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);
                initrandommealRecView(view);
                initCategoryRecView(view);

        }

        void initCategoryRecView(View view) {
                categoryrecyclerView = (RecyclerView) view.findViewById(R.id.category_rec_view);
                categoryrecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                categoryrecyclerView.setLayoutManager(layoutManager);
                List arrayList = Arrays.asList(new Category(
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Female_house_sparrow_at_Kodai.jpg/280px-Female_house_sparrow_at_Kodai.jpg",
                                "Cake1 Desc"),
                                new Category("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Female_house_sparrow_at_Kodai.jpg/280px-Female_house_sparrow_at_Kodai.jpg",
                                                "cacke"));
                Categoriesadapter mAdapter = new Categoriesadapter(arrayList, view.getContext());
                categoryrecyclerView.setAdapter(mAdapter);
        }

        void initrandommealRecView(View view) {
                randomrecyclerView = (RecyclerView) view.findViewById(R.id.randomMealrecview);
                randomrecyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                randomrecyclerView.setLayoutManager(layoutManager);
                List<Meal> arrayList = Arrays.asList(new Meal(Long.getLong("5656565"), "Broccoli & Stilton soup",
                                null,
                                "Starter", "British",
                                "Heat the rapeseed oil in a large saucepan and then add the onions. Cook on a medium heat until soft. Add a splash of water if the onions start to catch. Add the celery, leek, potato and a knob of butter. Stir until melted, then cover with a lid. Allow to sweat for 5 minutes. Remove the lid. Pour in the stock and add any chunky bits of broccoli stalk. Cook for 10 – 15 minutes until all the vegetables are soft. Add the rest of the broccoli and cook for a further 5 minutes. Carefully transfer to a blender and blitz until smooth. Stir in the stilton, allowing a few lumps to remain. Season with black pepper and serve.",
                                "https://www.themealdb.com/images/media/meals/tvvxpv1511191952.jpg", null,
                                "https://www.youtube.com/watch?v=_HgVLpmNxTY", "Rapeseed Oil", "Onion", "Celery",
                                "Leek", "Potatoes", "Butter", "Vegetable Stock", "Broccoli",
                                "Stilton Cheese",
                                "", "", "", "", "",
                                "", "", "", "", "",
                                "", "2 tblsp ", "1 finely chopped ", "1",
                                "1 sliced", "1 medium", "1 knob", "1 litre hot",
                                "1 Head chopped", "140g", "", "",
                                "",
                                "", "", "", "", "", "", "", "",
                                "https://www.bbcgoodfood.com/recipes/1940679/broccoli-and-stilton-soup",
                                null, null, null),
                                new Meal(Long.getLong("52842"), "Broccoli & Stilton soup",
                                                null, "Starter", "British",
                                                "Heat the rapeseed oil in a large saucepan and then add the onions. Cook on a medium heat until soft. Add a splash of water if the onions start to catch. Add the celery, leek, potato and a knob of butter. Stir until melted, then cover with a lid. Allow to sweat for 5 minutes. Remove the lid. Pour in the stock and add any chunky bits of broccoli stalk. Cook for 10 – 15 minutes until all the vegetables are soft. Add the rest of the broccoli and cook for a further 5 minutes. Carefully transfer to a blender and blitz until smooth. Stir in the stilton, allowing a few lumps to remain. Season with black pepper and serve.",
                                                "https://www.themealdb.com/images/media/meals/tvvxpv1511191952.jpg",
                                                null, "https://www.youtube.com/watch?v=_HgVLpmNxTY", "Rapeseed Oil",
                                                "Onion", "Celery", "Leek", "Potatoes", "Butter", "Vegetable Stock",
                                                "Broccoli",
                                                "Stilton Cheese", "", "", "", "", "", "", "", "", "", "", "",
                                                "2 tblsp ", "1 finely chopped ", "1", "1 sliced", "1 medium", "1 knob",
                                                "1 litre hot", "1 Head chopped", "140g", "", "", "",
                                                "", "", "", "", "", "", "", "",
                                                "https://www.bbcgoodfood.com/recipes/1940679/broccoli-and-stilton-soup",
                                                null, null, null));
                CardViewAdapter mAdapter = new CardViewAdapter(arrayList, view.getContext());
                randomrecyclerView.setAdapter(mAdapter);
        }

}