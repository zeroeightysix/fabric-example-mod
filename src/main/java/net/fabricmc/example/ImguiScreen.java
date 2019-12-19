package net.fabricmc.example;

import glm_.vec2.Vec2;
import imgui.ImGui;
import imgui.ImguiKt;
import imgui.classes.Context;
import imgui.impl.gl.ImplGL3;
import imgui.impl.glfw.ImplGlfw;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import uno.glfw.GlfwWindow;

public class ImguiScreen extends Screen {

    private ImGui imgui = ImGui.INSTANCE;

    private ImplGL3 implGl3;
    private ImplGlfw implGlfw;

    private boolean[] showDemoWindow = new boolean[] { false };

    public ImguiScreen() {
        super(new LiteralText("ImGUI Minecraft Screen"));
        setup();
    }

    private void setup() {
        ImguiKt.MINECRAFT_BEHAVIORS = true;
        GlfwWindow window = GlfwWindow.from(MinecraftClient.getInstance().getWindow().getHandle());
        window.makeContextCurrent();
        new Context();
        implGlfw = new ImplGlfw(window, false, null);
        implGl3 = new ImplGL3();
    }

    public void reload() {
        implGl3 = new ImplGL3();
    }

    @Override
    public void render(int x, int y, float partialTicks) {

        implGl3.newFrame();
        implGlfw.newFrame();
        imgui.newFrame();

        imgui.text("Hello world!");
        if (imgui.button("Open demo window", new Vec2())) {
            showDemoWindow[0] = true;
        }

        if (showDemoWindow[0]) {
            imgui.showDemoWindow(showDemoWindow);
        }

        imgui.render();
        implGl3.renderDrawData(imgui.getDrawData());

    }

}
