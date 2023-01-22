package com.example.sidechef;

import android.app.AlertDialog;
import android.content.Context;

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


    public static WeekMeals converter (String day, String time, Meal meal) {
        WeekMeals weekMeals = new WeekMeals();
        weekMeals.setDay(day);
        weekMeals.setTime(time);
        weekMeals.setIdMeal(meal.getIdMeal());
        weekMeals.setStrMeal( meal.getStrMeal());
//        this.strDrinkAlternate = meal.getStrDrinkAlternate();
//        this.strCategory = meal.getStrCategory();
//        this.strArea = meal.getStrArea();
//        this.strInstructions = meal.getStrInstructions();
weekMeals.setStrMealThumb(meal.getStrMealThumb());
//        this.strTags = meal.getStrTags();
//        this.strYoutube = meal.getStrYoutube();
//        this.strSource = meal.getStrSource();wee
 weekMeals.setStrImageSource( meal.getStrImageSource());
//        this.strCreativeCommonsConfirmed = meal.getStrCreativeCommonsConfirmed();
//        this.dateModified = meal.getDateModified();
        return weekMeals;
    }


}


/*

     WeekMeals( String day, String time, Meal meal) {

        this.day = day;
        Time = time;
        this.idMeal = meal.getIdMeal();
        this.strMeal = meal.getStrMeal();
        this.strDrinkAlternate = meal.getStrDrinkAlternate();
        this.strCategory = meal.getStrCategory();
        this.strArea = meal.getStrArea();
        this.strInstructions = meal.getStrInstructions();
        this.strMealThumb = meal.getStrMealThumb();
        this.strTags = meal.getStrTags();
        this.strYoutube = meal.getStrYoutube();
        this.strIngredient1 =meal.getStrIngredient1();
        this.strIngredient2 =meal.getStrIngredient2();
        this.strIngredient3 = meal.getStrIngredient3();
        this.strIngredient4 = meal.getStrIngredient4();
        this.strIngredient5 = meal.getStrIngredient5();
        this.strIngredient6 = meal.getStrIngredient6();
        this.strIngredient7 = meal.getStrIngredient7();
        this.strIngredient8 = meal.getStrIngredient8();
        this.strIngredient9 = meal.getStrIngredient9();
        this.strIngredient10 = meal.getStrIngredient10();
        this.strIngredient11 = meal.getStrIngredient11();
        this.strIngredient12 = meal.getStrIngredient12();
        this.strIngredient13 = meal.getStrIngredient13();
        this.strIngredient14 = meal.getStrIngredient14();
        this.strIngredient15 = meal.getStrIngredient15();
        this.strIngredient16 = meal.getStrIngredient16();
        this.strIngredient17 = meal.getStrIngredient17();
        this.strIngredient18 = meal.getStrIngredient18();
        this.strIngredient19 = meal.getStrIngredient19();
        this.strIngredient20 = meal.getStrIngredient20();
        this.strMeasure1 = meal.getStrMeasure1();
        this.strMeasure2 = meal.getStrMeasure2();
        this.strMeasure3 = meal.getStrMeasure3();;
        this.strMeasure4 = meal.getStrMeasure4();;
        this.strMeasure5 = meal.getStrMeasure5();;
        this.strMeasure6 = meal.getStrMeasure6();;
        this.strMeasure7 = meal.getStrMeasure7();;
        this.strMeasure8 = meal.getStrMeasure8();;
        this.strMeasure9 = meal.getStrMeasure9();;
        this.strMeasure10 = meal.getStrMeasure10();;
        this.strMeasure11 = meal.getStrMeasure11();;
        this.strMeasure12 = meal.getStrMeasure12();;
        this.strMeasure13 = meal.getStrMeasure13();;
        this.strMeasure14 = meal.getStrMeasure14();;
        this.strMeasure15 = meal.getStrMeasure15();;
        this.strMeasure16 = meal.getStrMeasure16();;
        this.strMeasure17 = meal.getStrMeasure17();;
        this.strMeasure18 = meal.getStrMeasure18();;
        this.strMeasure19 = meal.getStrMeasure19();;
        this.strMeasure20 = meal.getStrMeasure20();;
        this.strSource = meal.getStrSource();
        this.strImageSource = meal.getStrImageSource();
        this.strCreativeCommonsConfirmed = meal.getStrCreativeCommonsConfirmed();
        this.dateModified = meal.getDateModified();
    }

 */

/*
           //adapterConnector.sendData(data.get(position))
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
                    builderSingle.setIcon(R.drawable.ic_favorite);
                    builderSingle.setTitle("Select day:-");

                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice);
                    arrayAdapter.add(Week.Saturday.toString());
                    arrayAdapter.add(Week.Sunday.toString());
                    arrayAdapter.add(Week.Monday.toString());
                    arrayAdapter.add(Week.Tuesday.toString());
                    arrayAdapter.add(Week.Wednesday.toString());
                    arrayAdapter.add(Week.Thursday.toString());
                    arrayAdapter.add(Week.Friday.toString());
                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strName = arrayAdapter.getItem(which);
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(context);
                            builderInner.setMessage(strName);
                            builderInner.setTitle("Your Selected ");
                            builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,int which) {
                                  WeekMeals weekMeals = Utils.converter(strName,"breakfast",data.get(position));
                                    repository.insertdaily(weekMeals);
                                    dialog.dismiss();
                                }
                            });
                            builderInner.show();
                        }
                    });
                    builderSingle.show();
                }
 */
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
 //adapterConnector.sendData(data.get(position))
//                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
//                    builderSingle.setIcon(R.drawable.ic_favorite);
//                    builderSingle.setTitle("Select day:-");
//
//                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice);
//                    arrayAdapter.add(Week.Saturday.toString());
//                    arrayAdapter.add(Week.Sunday.toString());
//                    arrayAdapter.add(Week.Monday.toString());
//                    arrayAdapter.add(Week.Tuesday.toString());
//                    arrayAdapter.add(Week.Wednesday.toString());
//                    arrayAdapter.add(Week.Thursday.toString());
//                    arrayAdapter.add(Week.Friday.toString());
//                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String strName = arrayAdapter.getItem(which);
//                            AlertDialog.Builder builderInner = new AlertDialog.Builder(context);
//                            builderInner.setMessage(strName);
//                            builderInner.setTitle("Your Selected ");
//                            builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog,int which) {
//                                    //inser here
//
//                           //         WeekMeals weekMeals = Utils.converter("friday","breakfast");
//                                    repository.insertdaily(weekMeals);
//                                    dialog.dismiss();
//                                }
//                            });
//                            builderInner.show();
//                        }
//                    });
//                    builderSingle.show();
//                }
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