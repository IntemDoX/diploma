package models;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

import java.util.ArrayList;


/**
 * С помощью этог класса мы получаем спосик всех рабочик программ(не процессов!).
 * Случайно выявил, что активное окно записывается в список под индексом 1. Так его и получаю.
 * Объяснять код не смогу, так как нашел его и делал много чего наугад :D
 */
public class WindowsFactory {
    public static ArrayList<String> winList = new ArrayList<>();
    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
        int GetWindowTextA(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);
//        int GetWindowText(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);
        int GetWindowTextW(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);
//        int GetWindowTextU(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);
        int GetWindowLongA(WinDef.HWND hWnd, int var2);
    }


    public static void takeActWin() {
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            int count = 0;
            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                    user32.GetWindowTextA(hWnd, windowText, 512);
                   String wText = Native.toString(windowText);

                    //int visibleWindow = 0x00800000 | 0x00C00000;//WinUser.WS_BORDER | WS_VISIBLE;
                    long visibleWindow = 0x00800000L | 0x10000000L;   //ПОЛУЧАЕМ ВСЕ ОКНА!!!!
                    //long visibleWindow = 0x10000000L;   //ПОЛУЧАЕМ ВСЕ ОКНА!!!!
                    if ((user32.GetWindowLongA(hWnd, WinUser.GWL_STYLE) & visibleWindow) == visibleWindow) {
                        // если истина, то окно видно
                        winList.add(wText);
                        //System.out.println(wText);

                    }
                    if (wText.isEmpty()){
                        return true;
                    }
                  /*  System.out.println("Found window with text " + hWnd + ", total " + ++count
                            + " Text: " + wText );*/
                    return true;
            }
        },null);
    }
}
