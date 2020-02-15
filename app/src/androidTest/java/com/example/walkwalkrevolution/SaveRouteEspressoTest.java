package com.example.walkwalkrevolution;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.walkwalkrevolution.routemanagement.Route;
import com.example.walkwalkrevolution.routemanagement.RoutesManager;

import org.junit.Rule;
import org.junit.Test;

import java.util.Iterator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SaveRouteEspressoTest {
    private final long STEPS = 500;
    private final long TIME = 1200;
    private final double DISTANCE = 0.3;
    private SharedPreferences sp;
    private RoutesManager routesManager;

    public SaveRouteEspressoTest(){
         sp = new MockSharedPreference();
         routesManager = new RoutesManager(sp);
    }

    @Rule
    public ActivityTestRule<EnterRouteInfoActivity> activity = new ActivityTestRule<EnterRouteInfoActivity>(EnterRouteInfoActivity.class){
        @Override
        protected Intent getActivityIntent(){
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, EnterRouteInfoActivity.class);
            intent.putExtra(DataKeys.ROUTE_MANAGER_KEY, routesManager);
            intent.putExtra(DataKeys.STEPS_KEY, STEPS);
            intent.putExtra(DataKeys.DISTANCE_KEY, DISTANCE);
            intent.putExtra(DataKeys.TIME_KEY, TIME);
            return intent;
        }
    };

    @Test
    public void testSaveWalk(){
        String routeName = "My Route";
        String startLoc = "home";

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.routeName)).perform(replaceText(routeName));

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.startLoc)).perform(replaceText(startLoc));

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.saveButton)).perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //routesManager.saveRoute(null,
        //       new Route(routeName, startLoc, STEPS, DISTANCE, TIME));

        Iterator routesIterator = routesManager.iterator();
        assertTrue(routesIterator.hasNext());
        Route route = (Route) routesIterator.next();
        assertEquals(route.getName(), routeName);
        assertEquals(route.getDistance(), DISTANCE);
        assertEquals(route.getStartLoc(), startLoc);
        assertEquals(route.getSteps(), STEPS);
        assertEquals(route.getTime(), TIME);
    }
}