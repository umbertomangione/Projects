using UnityEngine;

public class ChangeController : MonoBehaviour
{
    public static int command;

    public void KeyboardCommand()
    {
        command = 1;
        Debug.Log(command);
    }

    public void MouseCommand()
    {
        command = 2;
        Debug.Log(command);
    }
}
