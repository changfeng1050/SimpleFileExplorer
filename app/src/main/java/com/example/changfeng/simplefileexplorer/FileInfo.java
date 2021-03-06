/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * This file is part of FileExplorer.
 *
 * FileExplorer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FileExplorer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.changfeng.simplefileexplorer;

public class FileInfo {

    public String fileName;

    public String filePath;

    public long fileSize;

    public boolean isdir;

    public int count;

    public long modifiedDate;

    public boolean selected;

    public boolean canRead;

    public boolean canWrite;

    public boolean isHidden;

    public long dbId; // id in the database, if is from database

    public FileInfo(String fileName, String filePath, boolean isDir) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.isdir = isDir;
    }


}
