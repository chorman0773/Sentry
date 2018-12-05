ShadeNBT is the Primary Save method of the Sentry Game Engine.
ShadeNBT is based on the NBT Specification developed by Marcus Person for Minecraft. Details about the nbt specification can be found at <https://wiki.vg/nbt>
The Current ShadeNBT Specification Version is 1.2 which implements version 19133 of the NBT Specification.
If the file indicates Version 1.0 then the File implements version 19132 of the NBT Specification. In particular, the NBT_TagLong (Tag Id 12) will not appear in files indicating Version 1.0 of the Shade Specification.

The Primary ShadeNBT File will have the following form, where nbt_tag represents a well formed NBT Tag as defined in the NBT Specification Version 19133.
```c
struct shade_file{
  shade_header header;
  nbt_tag nbtfile;
};
```

```c
struct shade_header{
u4 shade_magic;
version shade_version;
u1 shadeFlags;
};
```

Where shade_magic must be the 4 bytes `[AD 4E 42 54]`.
the version structure is defined as below:
```c
struct version{
u1 major;
u1 minor;
};
```
Where the version reprsented by the structure is composed of the major+1, and the minor. For example, The Version 1.2 is reprsented as `[0x00 0x02]`. This can be reduced to a single Big-Endian 2-byte version 0x0002.

If the Version in the Shade Header is 1.1 or less, the shadeFlags byte does not exists and should be ignored.
If the Version in the Shade Header is 1.2, then shadeFlags defines miscellaneous flags about the file.
In Version 1.2, only bit 0x80 is used in shadeFlags, which is the ByteOrder flag. When Set, the multibyte neumeric types defined in the NBT Specification are stored in Little-Endian ByteOrder (That is, the least significant byte has the lowest address occupied by the value, ie the 2-byte number 0xADBE is stored as the bytes `[BE AD]`.). Otherwise the byte order is Big-Endian (The least significant byte has the highest address occupied by the value, ie the 2-byte number 0xADBE is stored as the bytes `AD BE]`.). If the version is 1.1 or less, the ByteOrder flag should be considered clear and all multibyte numbers should be interpreted as Big-Endian. No Sentry Applications will set the ByteOrder flag and will always write in Big-Endian mode.


<h1>CryptoShade</h1>
CryptoShade is a subspecification of ShadeNBT, which stores password protected Save data. CryptoShade is optional in the specification and neither the ability to read nor write CryptoShade files is required.
CryptoShade uses the following structure for Save files

```c
struct crypto_file{
  shade_header header;
  crypto_header cryptoInfo;
  u1 encrypted_nbt_file[16][cryptoInfo.length];
};
```

The same shade_header is used, except that shade_magic must be the bytes `[EC 4E 42 54]`.
The crypto_header is defined by the following structure

```c
struct crypto_header{
  u1 salt[32];
  u1 cbcIv[16];
  u4 length;
};
```

Where the length is the number of 16-bytes blocks.

The File is encrypted using AES-256 in CBC Mode, with PKCS5 Padding. The Key for the Cipher is Derived using SHA-256 from the password bytes, encoded in UTF-8, with the salt bytes appended to the password. The salt and Iv should be obtained from a cryptographically strong random number source each time the file is saved. If a cryptographically strong random number source is not avaible, but a source of non-deterministic numbers is avaible, the salt and Iv can be derived in a Cryptographically Strong way from numbers produced in a non-deterministic way. If neither option is avaible, then it is strongly recommended that you do not provide password protection as an option. Writing a cryptoshade file mandates that at least one of these values must be unique between saves and must be obtained in a Cryptographically Strong manner. 

The Unencrypted file must be a well formed NBT File as above.
  

<h1>Changelog</h1>
<table>
  <tr>
    <th>Version</th>
    <th>Changes</th>
  </tr>
  <tr>
    <td>1.0</td>
    <td>Original ShadeNBT Version</td>
  </tr>
  <tr>
    <td>1.1</td>
    <td>Updated ShadeNBT to use the Current NBT Specification and added TAG_LongArray (tag 12) to the Specification</td>
  </tr>
  <tr>
    <td>1.2</td>
    <td>Added shadeFlags to the Specification, and reserves the most significant bit (0x80) to specify a Little-Endian File.</td>
  </tr>
</table>

