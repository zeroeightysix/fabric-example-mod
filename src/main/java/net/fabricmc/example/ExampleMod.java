package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {

	public static ImguiScreen imguiScreen;

	@Override
	public void onInitialize() {
		System.out.println("Hello imgui!");
	}

}
