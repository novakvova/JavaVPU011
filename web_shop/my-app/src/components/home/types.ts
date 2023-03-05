export interface ICategoryItem {
    id: number,
    name: string,
    image: string,
    description: string
}

export interface ICategoryCreate {
    name: string;
    file: File|null;
    description: string;
};