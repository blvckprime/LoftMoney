package com.example.loftmoney.screens.main;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

//   public static final String EXPENSE = "expense";
//   public static final String INCOME = "income";
//
//   private MoneyCellAdapter moneyCellAdapter;
//   private MainViewModel mainViewModel;
//
//
//   @Override
//   protected void onCreate(Bundle savedInstanceState) {
//       super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_main);
//
//       configureViews();
//       configureViewModel();
//
//   }
//
//   @Override
//   protected void onResume() {
//       super.onResume();
//
//       mainViewModel.loadIncomes(
//               ((LoftApp) getApplication()).moneyAPI,getSharedPreferences(getString(R.string.app_name),0)
//       );
//       mainViewModel.loadExpense(
//               ((LoftApp) getApplication()).moneyAPI,getSharedPreferences(getString(R.string.app_name),0)
//       );
//   }
//
//
//   private void configureViews() {
//       TabLayout tabLayout = findViewById(R.id.tabs);
//
//       final ViewPager viewPager = findViewById(R.id.viewpager);
//       final MainActivity.BudgetPagerAdapter adapter = new MainActivity.BudgetPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//
//       viewPager.setAdapter(adapter);
//
//       FloatingActionButton callAddButton = findViewById(R.id.add_item_fab);
//       callAddButton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(final View v) {
//               final int activeFragmentIndex = viewPager.getCurrentItem();
//               Fragment activeFragment = getSupportFragmentManager().getFragments().get(activeFragmentIndex);
//               activeFragment.startActivityForResult(new Intent(MainActivity.this, AddItemActivity.class),
//                       MoneyFragment.REQUEST_CODE);
//           }
//       });
//
//       tabLayout.setupWithViewPager(viewPager);
//       tabLayout.getTabAt(0).setText(R.string.expences_hint);
//       tabLayout.getTabAt(1).setText(R.string.income);
//   }
//
//   private void configureViewModel() {
//       mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//       mainViewModel.moneyItemsList.observe(this, moneyItems -> {
//           moneyCellAdapter.setData(moneyItems);
//       });
//
//       mainViewModel.messageString.observe(this, message -> {
//           if (!message.equals("")) {
//               showToast(message);
//           }
//       });
//
//       mainViewModel.messageInt.observe(this, message -> {
//           if (message > 0) {
//               showToast(getString(message));
//           }
//       });
//   }
//
//   private void showToast(String message) {
//       Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//   }
//
//   static class BudgetPagerAdapter extends FragmentPagerAdapter {
//
//       public BudgetPagerAdapter(@NonNull final FragmentManager fm, final int behavior) {
//           super(fm, behavior);
//       }
//
//       @NonNull
//       @Override
//       public Fragment getItem(final int position) {
//           switch (position) {
//               case 0:
//                   return MoneyFragment.newInstance(R.color.sky_blue, EXPENSE);
//               case 1:
//                   return MoneyFragment.newInstance(R.color.apple_green, INCOME);
//               default:
//                   return null;
//           }
//       }
//
//       @Override
//       public int getCount() {
//           return 2;
//       }
//   }
//
}