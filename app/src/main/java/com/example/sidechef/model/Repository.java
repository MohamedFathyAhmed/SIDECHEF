package com.example.sidechef.model;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.sidechef.model.data.api.ApiCalls;
import com.example.sidechef.model.data.api.Network;
import com.example.sidechef.model.data.database.MealsDatabase;
import com.example.sidechef.model.data.database.MealsDAO;
import com.example.sidechef.model.data.database.PlaneDAO;
import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;
import com.example.sidechef.model.models.WeekMeals;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

import retrofit2.Retrofit;

public class Repository {
    ApiCalls api;
    MealsDAO db;
    PlaneDAO pDB;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    private static Repository instance = null;
    private Repository(Context context) {
        Retrofit network = Network.getInstance(context);
        api = network.create(ApiCalls.class);
        MealsDatabase productDatabase = MealsDatabase.getInstance(context);
        db = productDatabase.mealsDAO();
        pDB = productDatabase.planeDAO();
    }

    public static synchronized Repository getInstance(Context context){
        if (instance == null)
            instance = new Repository(context);
        return instance;
    }

    private void addDataToFirestore(Meal meal) {

        // creating a collection reference
        // for our Firebase Firestore database.
        CollectionReference dbFav= fdb.collection("FAV");

        // adding our data to our courses object class.
     //   Courses courses = new Courses(courseName, courseDescription, courseDuration);

        // below method is use to add data to Firebase Firestore.
        dbFav.add(meal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
           }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
          }
        });
    }
    public void getAllMeals(RandomMealResponseDelegate randomMealResponseDelegate) {
        ArrayList<Observable<Meals>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(api.getMeal());

        }

        Single<List<Meals>> observable = Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())//UpStream operation
                .flatMap(new Function<Observable<Meals>, ObservableSource<? extends Meals>>() {
                    @Override
                    public ObservableSource<? extends Meals> apply(Observable<Meals> mealsListObservable) throws Throwable {
                        return mealsListObservable;
                    }
                })
                .collect(new Supplier<List<Meals>>() {
                    @Override
                    public List<Meals> get() throws Throwable {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<List<Meals>, Meals>() {
                    @Override
                    public void accept(List<Meals> mealsLists, Meals mealsList) throws Throwable {
                        mealsLists.add(mealsList);
                    }
                }).observeOn(AndroidSchedulers.mainThread());


        SingleObserver<List<Meals>> singleMealObserver = new SingleObserver<List<Meals>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Meals> mealsLists) {
                randomMealResponseDelegate.onSuccessResponse(mealsLists);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
        observable.subscribe(singleMealObserver);
    }
    public void getAllCategories(onGetCategoriesResponseDelegate getCategoriesResponse){
       Single<Categories> observable= api.getCategories().
               subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<Categories> categorysSingleObserver = new SingleObserver<Categories>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Categories categorys) {
                System.out.println("DDDDDDDDDDDDDDddd");
                getCategoriesResponse.onGetCategoriesSuccessResponse(categorys);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
            }
        };
        observable.subscribe(categorysSingleObserver);
    }


    public void getAll(DBResponse dbResponse){

            Flowable<List<Meal>> list=db.getAll();
            list.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{
                dbResponse.onSuccessResponse(item);
            },error->{error.getMessage();});


    };

    public LiveData<List<WeekMeals>> getweek(String day){
        final List<WeekMeals>[] meals = new List[]{null};
        LiveData<List<WeekMeals>> list=pDB.getForDay(day);
//        list.subscribeOnn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item->{ dbResponse.onSuccessResponse(item);
//        },error->{error.getMessage();});
return list;

    }





    public void insert(Meal meal){
        this.addDataToFirestore(meal);
        new Thread(() ->
                db.insert(meal)).start();
    };


    public void insertdaily(WeekMeals meal){
        new Thread(() -> pDB.insert(meal)).start();
    };



    public void delete(Meal meal){
        new Thread(() -> db.delete(meal)).start();
    };

    public interface RandomMealResponseDelegate {
        void onSuccessResponse(List<Meals> meals);
        void onErrorResponse(String errorMessage);
    }
    public interface WeelMealResponseDelegate {
        void onSuccessResponse(List<WeekMeals> meals);
        void onErrorResponse(String errorMessage);
    }
    public interface onGetCategoriesResponseDelegate{
        void onGetCategoriesSuccessResponse(Categories categories);
        void onErrorResponse(String errorMessage);
    }

    public interface DBResponse{
        void onSuccessResponse(List<Meal> meals);
    }
    public interface DBResponseWeek{
        void onSuccessResponse(List<WeekMeals> meals);
    }

}
