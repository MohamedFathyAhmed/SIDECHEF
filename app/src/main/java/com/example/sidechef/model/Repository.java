package com.example.sidechef.model;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.SearchView;

import com.example.sidechef.model.data.api.ApiCalls;
import com.example.sidechef.model.data.api.Network;
import com.example.sidechef.model.data.database.MealsDatabase;
import com.example.sidechef.model.data.database.MealsDAO;
import com.example.sidechef.model.data.database.PlaneDAO;
import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.CountryListResponse;
import com.example.sidechef.model.models.FilterResponseModel;
import com.example.sidechef.model.models.IngredientResponse;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;
import com.example.sidechef.model.models.WeekMeals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
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
    Context context;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    YourPreference yourPrefrence;
    private static Repository instance = null;

    private Repository(Context context) {
        Retrofit network = Network.getInstance(context);
        api = network.create(ApiCalls.class);
        MealsDatabase productDatabase = MealsDatabase.getInstance(context);
        db = productDatabase.mealsDAO();
        pDB = productDatabase.planeDAO();
        this.context = context;
        yourPrefrence = YourPreference.getInstance(context);
    }

    public static synchronized Repository getInstance(Context context) {
        if (instance == null)
            instance = new Repository(context);
        return instance;

    }

    private void addMealToFireStore(Meal meal) {
        fdb.collection("Fav").document(yourPrefrence.getData("email")).collection("meals").document(meal.getIdMeal())
                .set(meal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "The Meal Added To Favorite", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "The Meal  Failed Added To Favorite Try Again", Toast.LENGTH_LONG)
                                .show();

                    }
                });
    }

    private void deleteMealToFireStore(Meal meal) {
        fdb.collection("Fav").document(yourPrefrence.getData("email")).collection("meals").document(meal.getIdMeal())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "The Meal delete To Favorite", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "The Meal  Failed delete To Favorite Try Again", Toast.LENGTH_LONG)
                                .show();

                    }
                });
    }

    private void deleteWeelMealToFireStore(WeekMeals meal) {
        fdb.collection("Week").document(yourPrefrence.getData("email")).collection("meals")
                .document(meal.getIdMeal() + meal.getDay())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "The Meal delete To Favorite", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "The Meal  Failed delete To Favorite Try Again", Toast.LENGTH_LONG)
                                .show();

                    }
                });
    }

    private void addWeelMealToFireStore(WeekMeals meal) {
        fdb.collection("Week").document(yourPrefrence.getData("email")).collection("meals")
                .document(meal.getIdMeal() + meal.getDay())
                .set(meal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "The Meal Added To Favorite", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "The Meal  Failed Added To Favorite Try Again", Toast.LENGTH_LONG)
                                .show();

                    }
                });
    }

    public void getAllMealFirebase() {
        fdb.collection("Fav").document(yourPrefrence.getData("email")).collection("meals").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("getAllMealFirebase", "onSuccess: LIST EMPTY");
                            return;
                        } else {

                            List<Meal> types = documentSnapshots.toObjects(Meal.class);
                            ArrayList<Meal> listOfStrings = new ArrayList<>(types.size());
                            listOfStrings.addAll(types);
                            insertlist(listOfStrings);
                            Toast.makeText(context, "Success recovery!!!", Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error getting data!!!", Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void getAllMealPlanFirebase() {
        fdb.collection("Week").document(yourPrefrence.getData("email")).collection("meals").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("getAllMealFirebase", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<WeekMeals> types = documentSnapshots.toObjects(WeekMeals.class);
                            ArrayList<WeekMeals> listOfStrings = new ArrayList<>(types.size());
                            listOfStrings.addAll(types);
                            insertlistPlan(listOfStrings);
                            Toast.makeText(context, "Success recovery!!!", Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error getting data!!!", Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void getMealById(String id, SearchView searchView) {
        Maybe<Meals> mealsMaybe = api.getMealById(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        MaybeObserver<Meals> mealsMaybeObserver = new MaybeObserver<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Meals meals) {
                searchView.OnGetMealByIdSuccess(meals);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                searchView.OnGetMealByIdFailure(e.getMessage());
            }

            @Override
            public void onComplete() {
                searchView.OnGetMealByIdComplete();
            }
        };
        mealsMaybe.subscribe(mealsMaybeObserver);
    }

    public void getAllMeals(RandomMealResponseDelegate randomMealResponseDelegate) {
        ArrayList<Observable<Meals>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(api.getMeal());

        }

        Single<List<Meals>> observable = Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())// UpStream operation
                .flatMap(new Function<Observable<Meals>, ObservableSource<? extends Meals>>() {
                    @Override
                    public ObservableSource<? extends Meals> apply(Observable<Meals> mealsListObservable)
                            throws Throwable {
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

    public void getAllCategories(onGetCategoriesResponseDelegate getCategoriesResponse) {
        Single<Categories> observable = api.getCategories().subscribeOn(Schedulers.io())
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

    public void getMealByName(String mealName, OnGetMealByName onGetMealByName) {
        Single<Meals> observable = api.getMealByName(mealName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<Meals> mealsSingleObserver = new SingleObserver<Meals>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Meals meals) {
                onGetMealByName.OnGetMealByNameSuccess(meals);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                onGetMealByName.OnGetMealByNameFailure(e.getMessage());
            }
        };
        observable.subscribe(mealsSingleObserver);
    }

    public void getAllIngredients(String ingredientName, OnGetIngredientList onGetIngredientList) {

        Single<IngredientResponse> observable = api.getAllIngredients(ingredientName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<IngredientResponse> ingredientResponseSingleObserver = new SingleObserver<IngredientResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull IngredientResponse ingredientResponse) {
                System.out.println("DDDDDDDDDDDDDDddd");
                onGetIngredientList.OnGetIngredientListSuccess(ingredientResponse);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
                onGetIngredientList.OnGetIngredientListFailure(e.getMessage());
            }
        };
        observable.subscribe(ingredientResponseSingleObserver);
    }

    public void getAllCountries(String countryName, OnGetCountryList onGetCountryList) {
        Single<CountryListResponse> countryListResponseSingle = api.getAllCountries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<CountryListResponse> countryListResponseSingleObserver = new SingleObserver<CountryListResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull CountryListResponse countryListResponse) {
                onGetCountryList.OnGetCountryListSuccess(countryListResponse);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                onGetCountryList.OnGetCountryListFailure(e.getMessage());
            }
        };
        countryListResponseSingle.subscribe(countryListResponseSingleObserver);
    }

    public void getMealByCountry(String countryName, OnGetMealByFilter onGetMealByFilter) {
        Single<FilterResponseModel> filterResponseModelSingle = api.getMealByCountry(countryName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        SingleObserver<FilterResponseModel> filterResponseModelSingleObserver = new SingleObserver<FilterResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull FilterResponseModel filterResponseModel) {
                onGetMealByFilter.OnGetMealByFilterSuccess(filterResponseModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                onGetMealByFilter.OnGetMealByFilterFailure(e.getMessage());
            }
        };
        filterResponseModelSingle.subscribe(filterResponseModelSingleObserver);
    }

    public void getMealByIngredient(String ingredientName, OnGetMealByFilter onGetMealByFilter) {
        Single<FilterResponseModel> filterResponseModelSingle = api.getMealByIngredient(ingredientName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        SingleObserver<FilterResponseModel> filterResponseModelSingleObserver = new SingleObserver<FilterResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull FilterResponseModel filterResponseModel) {
                onGetMealByFilter.OnGetMealByFilterSuccess(filterResponseModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                onGetMealByFilter.OnGetMealByFilterFailure(e.getMessage());
            }
        };
        filterResponseModelSingle.subscribe(filterResponseModelSingleObserver);
    }

    // public void getMealByCategory(String categoryName,OnGetMealByFilter
    // onGetMealByFilter){
    // Single<FilterResponseModel>
    // filterResponseModelSingle=api.getMealByCategory(categoryName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    // SingleObserver <FilterResponseModel>filterResponseModelSingleObserver=new
    // SingleObserver<FilterResponseModel>() {

    // public void getMealByCountry(String countryName, SearchView searchView) {
    // Single<FilterResponseModel> filterResponseModelSingle =
    // api.getMealByCountry(countryName)
    // .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    // SingleObserver<FilterResponseModel> filterResponseModelSingleObserver = new
    // SingleObserver<FilterResponseModel>() {

    // @Override
    // public void onSubscribe(@NonNull Disposable d) {

    // }

    // @Override
    // public void onSuccess(@NonNull FilterResponseModel filterResponseModel) {
    // onGetMealByFilter.OnGetMealByFilterSuccess(filterResponseModel);
    // }

    // @Override
    // public void onError(@NonNull Throwable e) {
    // onGetMealByFilter.OnGetMealByFilterFailure(e.getMessage());
    // }
    // };
    // filterResponseModelSingle.subscribe(filterResponseModelSingleObserver);
    // }

    public void getMealByIngredient(String ingredientName, SearchView searchView) {
        Single<FilterResponseModel> filterResponseModelSingle = api.getMealByIngredient(ingredientName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        SingleObserver<FilterResponseModel> filterResponseModelSingleObserver = new SingleObserver<FilterResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull FilterResponseModel filterResponseModel) {
                searchView.OnGetMealByFilterSuccess(filterResponseModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                searchView.OnGetMealByFilterFailure(e.getMessage());
            }
        };
        filterResponseModelSingle.subscribe(filterResponseModelSingleObserver);
    }

    public void getMealByCategory(String categoryName, SearchView searchView) {
        Single<FilterResponseModel> filterResponseModelSingle = api.getMealByCategory(categoryName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        SingleObserver<FilterResponseModel> filterResponseModelSingleObserver = new SingleObserver<FilterResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull FilterResponseModel filterResponseModel) {
                searchView.OnGetMealByFilterSuccess(filterResponseModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                searchView.OnGetMealByFilterFailure(e.getMessage());
            }
        };
        filterResponseModelSingle.subscribe(filterResponseModelSingleObserver);
    }

    public void getAll(DBResponse dbResponse) {
        Flowable<List<Meal>> list = db.getAll();
        list.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            dbResponse.onSuccessResponse(item);
        }, error -> {
            error.getMessage();
        });
    };

    public synchronized LiveData<List<WeekMeals>> getweek(String day) {
        LiveData<List<WeekMeals>> list = pDB.getForDay(day);

        return list;

    }

    public void insertlist(ArrayList<Meal> meal) {
        new Thread(() -> db.insertAll(meal)).start();
    };

    public void insertlistPlan(ArrayList<WeekMeals> meal) {
        new Thread(() -> pDB.insertAll(meal)).start();
    };

    public void insert(Meal meal) {

        this.addMealToFireStore(meal);
        new Thread(() -> db.insert(meal)).start();
    };

    public void insertdaily(WeekMeals meal) {
        addWeelMealToFireStore(meal);
        new Thread(() -> pDB.insert(meal)).start();
    };

    public void deletedaily(WeekMeals meal) {
        deleteWeelMealToFireStore(meal);
        new Thread(() -> pDB.delete(meal)).start();
    };

    public void delete(Meal meal) {
        deleteMealToFireStore(meal);
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

    public interface onGetCategoriesResponseDelegate {
        void onGetCategoriesSuccessResponse(Categories categories);

        void onErrorResponse(String errorMessage);
    }

    public interface OnGetMealByName {
        void OnGetMealByNameSuccess(Meals meals);

        void OnGetMealByNameFailure(String errorMessage);
    }

    public interface OnGetMealByFilter {
        void OnGetMealByFilterSuccess(FilterResponseModel meals);

        void OnGetMealByFilterFailure(String errorMessage);
    }

    public interface OnGetIngredientList {

        void OnGetIngredientListSuccess(IngredientResponse ingredientResponse);

        void OnGetIngredientListFailure(String errorMessage);
    }

    public interface OnGetCountryList {
        void OnGetCountryListSuccess(CountryListResponse countryListResponse);

        void OnGetCountryListFailure(String errorMessage);
    }

    public interface DBResponse {
        void onSuccessResponse(List<Meal> meals);
    }

    public interface DBResponseWeek {
        void onSuccessResponse(List<WeekMeals> meals);
    }

}
