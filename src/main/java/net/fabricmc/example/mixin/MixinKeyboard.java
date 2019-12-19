package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.ImguiScreen;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

	/*
		This code is injected into minecraft code during runtime.
		this method will be called when any key is pressed.

		You cannot hot-reload any code from this method.
	 */
	@Inject(method = "onKey", at = @At(value = "RETURN", ordinal = 4), require = 1)
	public void onKey(long window, int key, int scancode, int i, int j, CallbackInfo info) {
		if (MinecraftClient.getInstance().player == null) return; // Don't do anything if not ingame

		System.out.println(key);
		System.out.println(scancode);

		if (i != 0) {
            if (key == GLFW.GLFW_KEY_Y) { // the 'y' key. i == 0 if the key was released.
                if (ExampleMod.imguiScreen == null) {
                    ExampleMod.imguiScreen = new ImguiScreen();
                }
                MinecraftClient.getInstance().openScreen(ExampleMod.imguiScreen);
            } else if (key == GLFW.GLFW_KEY_R) {
                if (ExampleMod.imguiScreen != null) {
                    ExampleMod.imguiScreen.reload();
                }
            }
        }
	}

}