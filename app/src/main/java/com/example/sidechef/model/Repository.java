package com.example.sidechef.model;

import android.content.Context;

import com.example.sidechef.model.data.api.ApiCalls;
import com.example.sidechef.model.data.api.Network;
import com.example.sidechef.model.data.database.MealsDatabase;
import com.example.sidechef.model.data.database.MealsDAO;
import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
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
    private static Repository instance = null;
    private Repository(Context context) {
        Retrofit network = Network.getInstance(context);
        api = network.create(ApiCalls.class);
        MealsDatabase productDatabase = MealsDatabase.getInstance(context);
        db = productDatabase.productsDAO();
    }

    public static synchronized Repository getInstance(Context context){
        if (instance == null)
            instance = new Repository(context);
        return instance;
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
        new Thread(() ->
                dbResponse.onSuccessResponse(db.getAll())
        ).start();
    };

    public void insert(Meal meal){
        new Thread(() -> db.insert(meal)).start();
    };


    public void delete(Meal meal){
        new Thread(() -> db.delete(meal)).start();
    };

    public interface RandomMealResponseDelegate {
        void onSuccessResponse(List<Meals> meals);
        void onErrorResponse(String errorMessage);
    }
    public interface onGetCategoriesResponseDelegate{
        void onGetCategoriesSuccessResponse(Categories categories);
        void onErrorResponse(String errorMessage);
    }

    public interface DBResponse{
        void onSuccessResponse(List<Meal> meals);
    }


}
