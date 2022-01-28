import { Server } from "./server";

export interface CustomResponse{
    servers: any;
    timeStamp: Date;
    statusCode: number;
    status: string;
    reason: string;
    message: string;
    developerMessage: string;
    data: { servers?: Server[], server?: Server };
};