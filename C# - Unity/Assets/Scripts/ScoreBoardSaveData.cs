using System;
using System.Collections.Generic;

namespace Project.Scoreboards
{
    [Serializable]
    public class ScoreBoardSaveData
    {
        public List<ScoreBoardEntryData> highscores = new List<ScoreBoardEntryData>();
    }
}
