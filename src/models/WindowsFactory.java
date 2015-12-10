package models;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

/**
 * С помощью этог класса мы получаем спосик всех рабочик программ(не процессов!).
 * Случайно выявил, что активное окно записывается в список под индексом 1. Так его и получаю.
 * Объяснять код не смогу, так как нашел его и делал много чего наугад :D
 */
public class WindowsFactory {

    private static ActiveWindowsStorage winList = ActiveWindowsStorage.getInstance();

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);
        WinDef.HWND GetForegroundWindow();
    }

    public static void takeActWin() {
        final User32 user32 = User32.INSTANCE;
       // user32.EnumWindows(new WinUser.WNDENUMPROC() {
        user32.EnumWindows((WinDef.HWND hWnd, Pointer arg1)-> {
           // @Override
          //  public boolean callback(WinDef.HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                PointerType hwnd = User32.INSTANCE.GetForegroundWindow(); // then you can call it!
                User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
                String wText = Native.toString(windowText);
                winList.add(wText);
                return true;
            //}
        },null);
    }
}
