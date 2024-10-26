using System.IO;
using UnityEngine;
using TMPro;
using UnityEngine.SceneManagement;

namespace Project.Scoreboards
{
    public class ScoreBoard : MonoBehaviour
    {
        [SerializeField] private int maxScoreBoardEntries = 5;
        [SerializeField] private Transform highscoreTransform = null;
        [SerializeField] private GameObject scoreBoardEntryObject = null;

        ScoreBoardEntryData testEntrydata = new ScoreBoardEntryData();

        public TMP_InputField Playername;

        private string SavePath => $"{Application.persistentDataPath}/highscore.json";

        private void Start()
        {
            ScoreBoardSaveData savedScores = GetSavedScores();

            UpdateUI(savedScores);

            SaveScores(savedScores);
        }

        void Update()
        {
            if (Input.GetKeyDown(KeyCode.Escape))
            {
                SceneManager.LoadScene("Menu");
            }
        }

        [ContextMenu("Add Test Entry")]
        public void AddTestEntry()
        {
            testEntrydata.entryName = Playername.text;
            testEntrydata.entryScore = PlayerPrefs.GetInt("Points");
            AddEntry(testEntrydata);
        }

        public void AddEntry(ScoreBoardEntryData scoreBoardEntryData)
        {
            ScoreBoardSaveData savedScores = GetSavedScores();

            bool scoreAdded = false;

            for(int i=0; i<savedScores.highscores.Count; i++)
            {
                if(scoreBoardEntryData.entryScore > savedScores.highscores[i].entryScore)
                {
                    savedScores.highscores.Insert(i, scoreBoardEntryData);
                    scoreAdded = true;
                    break;
                }
            }

            if(!scoreAdded && savedScores.highscores.Count < maxScoreBoardEntries)
                savedScores.highscores.Add(scoreBoardEntryData);

            if (savedScores.highscores.Count > maxScoreBoardEntries)
                savedScores.highscores.RemoveRange(
                    maxScoreBoardEntries, savedScores.highscores.Count - maxScoreBoardEntries);

            UpdateUI(savedScores);

            SaveScores(savedScores);
        }

        private void UpdateUI(ScoreBoardSaveData savedScores)
        {
            foreach(Transform child in highscoreTransform)
            {
                Destroy(child.gameObject);
            }

            foreach(ScoreBoardEntryData highscore in savedScores.highscores)
            {
                Instantiate(scoreBoardEntryObject, highscoreTransform).
                    GetComponent<ScoreBoardEntryUI>().Initialise(highscore);
            }
        }

        private ScoreBoardSaveData GetSavedScores()
        {
            if(!File.Exists(SavePath))
            {
                File.Create(SavePath).Dispose();
                return new ScoreBoardSaveData();
            }

            using (StreamReader stream = new StreamReader(SavePath))
            {
                string json = stream.ReadToEnd();

                return JsonUtility.FromJson<ScoreBoardSaveData>(json);
            }
        }

        private void SaveScores(ScoreBoardSaveData scoreBoardSaveData)
        {
            using(StreamWriter stream = new StreamWriter(SavePath))
            {
                string json = JsonUtility.ToJson(scoreBoardSaveData, true);
                stream.Write(json);
            }
        }
    }
}