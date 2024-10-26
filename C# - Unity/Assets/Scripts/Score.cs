using UnityEngine;
using UnityEngine.UI;

public class Score : MonoBehaviour
{
    public static int ScoreValue = 0;
    public Text score;

    void Update()
    {
        score.text = "Score: " + ScoreValue.ToString();
        PlayerPrefs.SetInt("Points", ScoreValue);
        PlayerPrefs.Save();
    }

    public void SetScore()
    {
        ScoreValue = 0;
    }
}
