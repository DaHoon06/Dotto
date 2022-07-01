export namespace IBoard {
  export interface CommonProp {
    createdAt: Date;
    modifiedAt: Date;
    deletedAt: Date;
    deletedYn: string;
  }

  export interface Dotto {
    title: string;
    content: string;
    price: string;
    salesPrice: string;
    salesYn: boolean;
    genre: string;
    totalTime: string;
    tag: string[];
    postPhoto?: File[];
  }

  export interface IFileUpload {
    file: FileUpload[];
    preview: string;
    number: number;
  }

  export interface FileUpload {
    lastModified: number,
    lastModifiedDate: Date,
    name: string,
    size: number,
    type: string,
    webkitRelativePath: string
  }
}