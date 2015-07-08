package com.tobyte.todo.gui;

import com.tobyte.todo.help.NBTHelper;
import com.tobyte.todo.help.Reference;
import com.tobyte.todo.help.RegisterHelper;
import com.tobyte.todo.message.Message;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

/**
 * Handles gui display and interaction
 */
public class Gui extends GuiScreen
{
    /**
     * Code of enter key
     */
    private static final Integer KEYCODE_ENTER = 28;

    /**
     * Editable text field
     */
    private GuiTextField textField;


    private int texWidth = 192;
    private int texHeight = 192;
    private int posX;
    private int posY;

    /**
     * Add Buttons an other elements to gui
     */
    public void initGui()
    {
        // Lets you press return continuously
        Keyboard.enableRepeatEvents(true);

        // Make scalable
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        this.posX = (scaledResolution.getScaledWidth() - texWidth) / 2;
        this.posY = (scaledResolution.getScaledHeight() - texHeight) / 2;

        // object, x, y, width, height
        textField = new GuiTextField(this.fontRendererObj, (this.posX + (texWidth - 100) / 2), this.posY + 15,  100, 12);
        textField.setEnableBackgroundDrawing(false);
        textField.setMaxStringLength(18);
        textField.setTextColor(0x00000011);
        textField.setFocused(true);
        textField.setText("sample text");
    }

    /**
     * Key handling
     * @param c typed character
     * @param code code of typed character
     */
    @Override
    protected void keyTyped(char c, int code)
    {
        super.keyTyped(c, code);

        // If enter pressed, save text
        if (code == KEYCODE_ENTER && textField.getText() != null && !textField.getText().isEmpty()) {
            NBTHelper.addStringToTagListByKey(Reference.STORAGE_KEY, mc.thePlayer.getHeldItem(), textField.getText());
            RegisterHelper.simpleNetworkWrapper.sendToServer(new Message(textField.getText()));

            textField.setText("");
        }

        this.textField.textboxKeyTyped(c, code);
    }

    /**
     * Mouse click handling
     * @param x Position x
     * @param y Position y
     * @param btn Mouse button
     */
    @Override
    protected void mouseClicked(int x, int y, int btn)
    {
        super.mouseClicked(x, y, btn);
        textField.mouseClicked(x, y, btn);

    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        textField.updateCursorCounter();

    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int parWidth, int parHeight, float par3)
    {
        drawDefaultBackground();

        // Draw list background
        mc.getTextureManager()
                .bindTexture(new ResourceLocation(Reference.MODID + ":" + "textures/gui/book.png"));
        drawTexturedModalRect(this.posX, this.posY, 0, 0, this.texWidth, this.texHeight);

        // Render input field
        textField.drawTextBox();

        // Render list entries
        Integer currentPos = this.posY + 30;
        NBTTagList entries = NBTHelper.getTagListByKey(Reference.STORAGE_KEY, mc.thePlayer.getHeldItem());
        if (entries != null) {
            for (int i = 0; i < entries.tagCount(); i++) {
                fontRendererObj.drawString(entries.getStringTagAt(i), (this.posX + (texWidth - 100) / 2), currentPos + 10, 0x00000011);
                currentPos += 10;
            }
        }

        super.drawScreen(parWidth, parHeight, par3);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(true);
    }
}
