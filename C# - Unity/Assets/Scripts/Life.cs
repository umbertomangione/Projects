using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Life : MonoBehaviour
{
    public Text NumberLife;

    public GameObject ImageLife;

    private void Start()
    {
        if (Screen.width == 640 && Screen.height == 480)
        {
            ImageLife.transform.Image();
            transform.Life();
        }
    }

    public void SetLife(int life)
    {
        NumberLife.text = "x"+life.ToString();
    }
}
