import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { ICategoryItem } from "./types";

const HomePage = () => {
  const [categories, setCategories] = useState<ICategoryItem[]>([]);

  useEffect(() => {
    axios
      .get<ICategoryItem[]>("http://localhost:8085/api/categories")
      .then((resp) => {
        const { data } = resp;
        setCategories(data);
      });
  }, []);

  const content = categories.map((category) => (
    <div key={category.id}>
      <div className="group relative">
        <div className="relative h-80 w-full overflow-hidden rounded-lg bg-white group-hover:opacity-75 sm:aspect-w-2 sm:aspect-h-1 sm:h-64 lg:aspect-w-1 lg:aspect-h-1">
          <div className="main-slider">
            <img
              src={"http://localhost:8085/files/600_" + category.image}
              alt={category.name}
              className="picture-container"
            />
          </div>
        </div>
        <h3 className="mt-6 text-sm text-gray-500">
          <a href="#">
            <span className="absolute inset-0" />
            {category.name}
          </a>
        </h3>
        <p className="text-base font-semibold text-gray-900">
          {category.description}
        </p>
      </div>
      <div className="my-2">
        <Link
          to="#"
          className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
          onClick={(e) => {
            e.preventDefault();
            console.log("click delete", category.id);
          }}
        >
          Видалить
        </Link>
      </div>
    </div>
  ));

  return (
    <>
      <div className="bg-gray-100">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="mx-auto max-w-2xl py-10 sm:py-16 lg:max-w-none lg:py-24">
            <h2 className="text-2xl font-bold text-gray-900">Collections</h2>

            <div className="py-2">
              <Link
                to="/categories/create"
                className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
              >
                Додати категорію
              </Link>
            </div>

            <div className="py-2">
              <Link
                to="/products/create"
                className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
              >
                Додати продукт
              </Link>
            </div>

            <div className="mt-6 space-y-12 lg:grid lg:grid-cols-3 lg:gap-x-6 lg:space-y-0">
              {content}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default HomePage;
