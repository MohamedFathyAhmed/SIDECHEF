package com.example.sidechef.Utils;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.WeekMeals;


public class Utils {

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo networkInfo : info) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    public static WeekMeals converter (String day, String time, Meal meal) {
        WeekMeals weekMeals = new WeekMeals();
        weekMeals.setDay(day);
        weekMeals.setTime(time);
        weekMeals.setIdMeal(meal.getIdMeal());
        weekMeals.setStrMeal( meal.getStrMeal());
        weekMeals.setStrCategory(meal.getStrCategory());
        weekMeals.setStrDrinkAlternate( meal.getStrDrinkAlternate());
        weekMeals.setStrArea(meal.getStrArea());
        weekMeals.setStrInstructions(meal.getStrInstructions());
weekMeals.setStrMealThumb(meal.getStrMealThumb());
        weekMeals.setStrTags(meal.getStrTags());
        weekMeals.setStrYoutube(meal.getStrYoutube());
        weekMeals.setStrSource(meal.getStrSource());
 weekMeals.setStrImageSource( meal.getStrImageSource());
 weekMeals.setStrCreativeCommonsConfirmed(meal.getStrCreativeCommonsConfirmed());
weekMeals.setDateModified(meal.getDateModified());
weekMeals.setStrIngredient1(meal.getStrIngredient1());
        weekMeals.setStrIngredient1(meal.getStrIngredient1());
        weekMeals.setStrIngredient2(meal.getStrIngredient2());
        weekMeals.setStrIngredient3(meal.getStrIngredient3());
        weekMeals.setStrIngredient4(meal.getStrIngredient4());
        weekMeals.setStrIngredient5(meal.getStrIngredient5());
        weekMeals.setStrIngredient6(meal.getStrIngredient6());
        weekMeals.setStrIngredient7(meal.getStrIngredient7());
        weekMeals.setStrIngredient8(meal.getStrIngredient8());
        weekMeals.setStrIngredient9(meal.getStrIngredient9());
        weekMeals.setStrIngredient10(meal.getStrIngredient10());
        weekMeals.setStrIngredient11(meal.getStrIngredient11());
        weekMeals.setStrIngredient12(meal.getStrIngredient12());
        weekMeals.setStrIngredient13(meal.getStrIngredient13());
        weekMeals.setStrIngredient14(meal.getStrIngredient14());
        weekMeals.setStrIngredient15(meal.getStrIngredient15());
        weekMeals.setStrIngredient16(meal.getStrIngredient16());
        weekMeals.setStrIngredient17(meal.getStrIngredient17());
        weekMeals.setStrIngredient18(meal.getStrIngredient18());
        weekMeals.setStrIngredient19(meal.getStrIngredient19());
        weekMeals.setStrIngredient20(meal.getStrIngredient20());
        weekMeals.setStrMeasure1(meal.getStrMeasure1());
        weekMeals.setStrMeasure2(meal.getStrMeasure2());
        weekMeals.setStrMeasure3(meal.getStrMeasure3());
        weekMeals.setStrMeasure4(meal.getStrMeasure4());
        weekMeals.setStrMeasure5(meal.getStrMeasure5());
        weekMeals.setStrMeasure6(meal.getStrMeasure6());
        weekMeals.setStrMeasure7(meal.getStrMeasure7());
        weekMeals.setStrMeasure8(meal.getStrMeasure8());
        weekMeals.setStrMeasure9(meal.getStrMeasure9());
        weekMeals.setStrMeasure10(meal.getStrMeasure10());
        weekMeals.setStrMeasure11(meal.getStrMeasure11());
        weekMeals.setStrMeasure12(meal.getStrMeasure12());
        weekMeals.setStrMeasure13(meal.getStrMeasure13());
        weekMeals.setStrMeasure14(meal.getStrMeasure14());
        weekMeals.setStrMeasure15(meal.getStrMeasure15());
        weekMeals.setStrMeasure16(meal.getStrMeasure16());
        weekMeals.setStrMeasure17(meal.getStrMeasure17());
        weekMeals.setStrMeasure18(meal.getStrMeasure18());
        weekMeals.setStrMeasure19(meal.getStrMeasure19());
        weekMeals.setStrMeasure20(meal.getStrMeasure20());

        return weekMeals;
    }


}


/*
//alert yes no
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Write your message here.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

 */


/*
public class WeekMeals  extends Meal{

//    @PrimaryKey(autoGenerate = true)
//    public int id;
public  String day ;
    public  String time ;


    public WeekMeals(String strday , String strtime ,Meal meal) {
        super(meal);
        day=strday;
        time=strtime;
    }
    public WeekMeals() {

    }
}
 */

/*
    String email  = YourPreference.getInstance(requireContext()).getData("email");
        if( email.equals("")){
            btnlinearLayout.setVisibility(View.GONE);
        }else {
            btnlinearLayout.setVisibility(View.VISIBLE);
        }

 */