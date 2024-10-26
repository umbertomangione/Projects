﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// Questo script contiene la lista degli oggetti che devono essere inseriti all'interno del gioco, partendo dai prefabs
[System.Serializable]
public class PoolingObjects
{
    public GameObject pooledPrefab;
    public int count;
}

public class PoolingController : MonoBehaviour {

    [Tooltip("Your 'pooling' objects. Add new element and add the prefab to create the object prefab")]
    public PoolingObjects[] poolingObjectsClass;

    //la lista dove gli oggetti da inserire sono "salvati"
    List<GameObject> pooledObjectsList = new List<GameObject>();

    public static PoolingController instance; 

    private void Awake()
    {
        if (instance == null)
            instance = this;
    }

    private void Start()
    {
        CreateNewList();        
    }

    void CreateNewList()
    {
        for (int i = 0; i < poolingObjectsClass.Length; i++)    //per ogni prefab crea il numero di oggetti di cui necessitiamo e li disattiva
        {
            for (int k = 0; k < poolingObjectsClass[i].count; k++)
            {
                GameObject newObj = Instantiate(poolingObjectsClass[i].pooledPrefab, transform);
                pooledObjectsList.Add(newObj);
                newObj.SetActive(false);                
            }
        }
    }

    
    public GameObject GetPoolingObject(GameObject prefab)   //tramite il nome del prefab ritorna gli oggetti giusti
    {
        string cloneName = GetCloneName(prefab);
        for (int i =0; i<pooledObjectsList.Count; i++)      
        {
            if (!pooledObjectsList[i].activeSelf && pooledObjectsList[i].name == cloneName)
            {                
                return pooledObjectsList[i];
            }
        }
        return AddNewObject(prefab);                        
    }

    GameObject AddNewObject(GameObject prefab)              //crea un nuovo oggetto e lo aggiunge alla lista
    {
        GameObject newObj = Instantiate(prefab, transform);
        pooledObjectsList.Add(newObj);
        newObj.SetActive(false);
        return newObj;
    }

    string GetCloneName(GameObject prefab)                  
    {
        return prefab.name + "(Clone)";
    }
}
