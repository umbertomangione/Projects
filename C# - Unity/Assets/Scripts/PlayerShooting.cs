using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Guns dalla gerarchia di player

[System.Serializable]
public class Guns
{
    public GameObject rightGun, leftGun, centralGun;
    [HideInInspector] public ParticleSystem leftGunVFX, rightGunVFX, centralGunVFX; 
}

public class PlayerShooting : MonoBehaviour {

    private AudioSource FireEffect;

    [Tooltip("projectile prefab")]
    public GameObject projectileObject;

    [Tooltip("current weapon power")]
    [Range(1, 4)]       
    public int weaponPower = 1;

    [Tooltip("current firerate")]
    public float firerate = .25f;

    public Guns guns;
    [HideInInspector] public int maxweaponPower = 4;
    [HideInInspector] public float canshoot = 0f;
    public static PlayerShooting instance;

    private void Awake()
    {
        if (instance == null)
            instance = this;
    }
    private void Start()
    {
        FireEffect = GetComponent<AudioSource>();
        guns.leftGunVFX = guns.leftGun.GetComponent<ParticleSystem>();
        guns.rightGunVFX = guns.rightGun.GetComponent<ParticleSystem>();
        guns.centralGunVFX = guns.centralGun.GetComponent<ParticleSystem>();
    }

    private void Update()
    {
        if (ChangeController.command == 2){
            if (Input.GetMouseButton(0) && Time.time > canshoot)
            {
                MakeAShot();
                canshoot = Time.time + firerate;
                FireEffect.Play();
            }
        }else{
            if (Input.GetKey(KeyCode.Space) && Time.time > canshoot)
            {
                MakeAShot();
                canshoot = Time.time + firerate;
                FireEffect.Play();
            }
        }
    }

    

    void MakeAShot() 
    {
        switch (weaponPower) 
        {
            case 1:
                CreateLazerShot(projectileObject, guns.centralGun.transform.position, Vector3.zero);
                guns.centralGunVFX.Play();
                break;
            case 2:
                CreateLazerShot(projectileObject, guns.rightGun.transform.position, Vector3.zero);
                guns.leftGunVFX.Play();
                CreateLazerShot(projectileObject, guns.leftGun.transform.position, Vector3.zero);
                guns.rightGunVFX.Play();
                break;
            case 3:
                CreateLazerShot(projectileObject, guns.centralGun.transform.position, Vector3.zero);
                CreateLazerShot(projectileObject, guns.rightGun.transform.position, new Vector3(0, 0, -5));
                guns.leftGunVFX.Play();
                CreateLazerShot(projectileObject, guns.leftGun.transform.position, new Vector3(0, 0, 5));
                guns.rightGunVFX.Play();
                break;
            case 4:
                CreateLazerShot(projectileObject, guns.centralGun.transform.position, Vector3.zero);
                CreateLazerShot(projectileObject, guns.rightGun.transform.position, new Vector3(0, 0, -5));
                guns.leftGunVFX.Play();
                CreateLazerShot(projectileObject, guns.leftGun.transform.position, new Vector3(0, 0, 5));
                guns.rightGunVFX.Play();
                CreateLazerShot(projectileObject, guns.leftGun.transform.position, new Vector3(0, 0, 15));
                CreateLazerShot(projectileObject, guns.rightGun.transform.position, new Vector3(0, 0, -15));
                break;
        }
    }

    void CreateLazerShot(GameObject lazer, Vector3 pos, Vector3 rot) 
    {
        Instantiate(lazer, pos, Quaternion.Euler(rot));
    }
}
