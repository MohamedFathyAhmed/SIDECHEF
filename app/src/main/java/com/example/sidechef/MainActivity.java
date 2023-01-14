package com.example.sidechef;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.sidechef.SingInActivity.View.SignIn;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FancyWalkthroughActivity {
Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard(" Your Favorite Recipes In One Place", "Save Time On Planning By Your Favorite Recipes Within Reach", R.drawable.ic_favorite);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard(" Your Favorite Recipes In One Place", "Save Time On Planning By Your Favorite Recipes Within Reach", R.drawable.ic_favorite);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard(" Your Favorite Recipes In One Place", "Save Time On Planning By Your Favorite Recipes Within Reach", R.drawable.ic_favorite);
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setTitleColor(R.color.black);
        fancywalkthroughCard1.setDescriptionColor(R.color.black);




        List<FancyWalkthroughCard> pages = new ArrayList<>();
        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setOnboardPages(pages);
        setImageBackground(R.drawable.ic_launcher_background);
    }



    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(this, SignIn.class));

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}