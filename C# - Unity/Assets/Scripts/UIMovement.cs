using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public static class UIMovement
{
    public static void Image(this Transform trans)
    {
        trans.position = new Vector2(50, trans.position.y);
    }

    public static void Life(this Transform trans)
    {
        trans.position = new Vector2(93, trans.position.y);
    }

    public static void On(this Text on)
    {
        on.text = "On";
    }

    public static void Off(this Text on)
    {
        on.text = "Off";
    }
}
