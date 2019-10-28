                    TEMPLATE GUIDELINE

1. STEP add activity to mvp template:
 - Add @ContributesAndroidInjector with module class, bind activity to ActivityBuilder
 ( ***NOTE: Use scope @PerActivity and don't use qualifier @ActivityContext)
 - Provide required component in this activity module class. This activity module has to be extended
 (include) BaseActivityModule and provide required component from BaseActivityModule, ex:
     @Binds
     @PerActivity
     abstract Activity activity(TestActivity activity);

2. STEP add fragment to mvp template:
 - Activity has to be MvpActivity or implement HasSupportFragmentInjector
 - Add inject member DispatchingAndroidInjector<Fragment> to this activity with scope @PerActivity
 ( ***NOTE***: Use scope @PerFragment)
 - Create fragment module and add to activity module with ContributesAndroidInjector, ex:
    @PerFragment
    @ContributesAndroidInjector(modules = TestFragmentModule.class)
    abstract TestFragment testFragment();
 - Provide required component in this fragment module. This fragment module has to be extended
 (include) BaseFragmentModule and provide required component from BaseFragmentModule, ex:
     @Provides
     @PerFragment
     Fragment bindFragment(TestFragment fragment) {
         return fragment;
     }
3. STEP add child fragment to fragment host in mvp template:
 - To be update.

4. Base guideline:
 - Activity has to be extended module's BaseActivity(different with project BaseActivity). If activity
 use dagger, it has to be extended MvpActivity
 - Fragment has to be extended module's BaseFragment(different with project BaseFragment).
 There are many fragment type to be extended, ex: BaseFragment, BaseDialog, ... If fragment
 use dagger, it has to be extended MvpFragment.

 - Activity or Fragment has to attach and detach Presenter (if use MVP)

 - Most important member in Activity to use:
    + Application context: mContext
    + Activity context: mActivityContext
    + Fragment Manager: mFragmentManager
    + Current dialog: mCurAlertDialog, help synchronize between all application dialog, so always
    use this alert dialog

 - Most important member in Fragment to use:
    + Application context: getApplicationContext()
    + Activity context: mActivityContext
    + Child fragment manager: mChildFragmentManager