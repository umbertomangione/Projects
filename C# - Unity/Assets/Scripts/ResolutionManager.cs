using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ResolutionManager : MonoBehaviour
{
    public int width;
    public int height;

    public static ResolutionManager instance;

    private void Awake()
    {
        if (instance == null)
            instance = this;
    }

    public void SetWidth(int newW)
    {
        width = newW;
    }

    public void SetHeight(int newH)
    {
        height = newH;
    }

    public void SetRes()
    {
        Screen.SetResolution(width, height, false);
    }
}