package com.example.sidechef;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.core.content.ContextCompat;

import com.example.sidechef.SignUpActivity.View.SignUp;
import com.example.sidechef.SingInActivity.View.SignIn;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard(" Personalized Recipe Discovery", "Tell us your food preferences and we'll serve you delicious recipes ideas.", R.drawable.baseline_timer_24);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard(" Grocery List For When You Shop", "Grocery List For When You Shop", R.drawable.baseline_restaurant_menu_24);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard(" Your Favorite Recipes In One Place", "Save Time On Planning By Your Favorite Recipes Within Reach", R.drawable.ic_favorite_border);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);



        List<FancyWalkthroughCard> pages = new ArrayList<>();
        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setColorBackground(R.color.black);
        setInactiveIndicatorColor(com.shashank.sony.fancywalkthroughlib.R.color.grey_300);
        setActiveIndicatorColor(com.shashank.sony.fancywalkthroughlib.R.color.grey_600);
        setImageBackground(R.drawable.fathul_abrar_unsplash);
        setOnboardPages(pages);
    }



    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(this, SignUp.class));

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}