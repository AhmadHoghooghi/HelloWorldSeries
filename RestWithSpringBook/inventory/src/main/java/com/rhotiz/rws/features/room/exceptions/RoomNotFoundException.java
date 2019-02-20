package com.rhotiz.rws.features.room.exceptions;

public class RoomNotFoundException extends RuntimeException{
    private Long roomId;

    public RoomNotFoundException(Long roomId) {
        this.roomId = roomId;
    }

    public RoomNotFoundException(String message, Long roomId) {
        super(message);
        this.roomId = roomId;
    }

    public RoomNotFoundException(String message, Throwable cause, Long roomId) {
        super(message, cause);
        this.roomId = roomId;
    }

    public RoomNotFoundException(Throwable cause, Long roomId) {
        super(cause);
        this.roomId = roomId;
    }

    public RoomNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Long roomId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
