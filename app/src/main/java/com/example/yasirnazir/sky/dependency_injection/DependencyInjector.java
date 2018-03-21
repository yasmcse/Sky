package com.example.yasirnazir.sky.dependency_injection;

import com.example.yasirnazir.sky.features.home.HomeActivity;
import com.example.yasirnazir.sky.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yasirnazir on 3/14/18.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface DependencyInjector {
    void inject(HomeActivity homeActivity);
}
