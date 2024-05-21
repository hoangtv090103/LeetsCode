import React from 'react';

type SignupProps = {
}

const Signup: React.FC<SignupProps> = () => {
  return (
    <div>
       <form className="space-y-6 px-6 py-5">
        <h3 className="text-x; font-medium text-white">Sign in to LeetsCode</h3>
        <div>
          <label
            htmlFor="email"
            className="block text-sm font-medium text-white mb-2 text-gray"
          >
            Your Email
          </label>
          <input
            type="email"
            id="email"
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-gray-300 text-white"
            placeholder="name@company.com"
          />
        </div>
        <div>
          <label
            htmlFor="password"
            className="text-usm font-medium block mb-2 text-gray-300"
          >
            Your Password
          </label>
          <input
            type="password"
            name="password"
            id="password"
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus border-blue-500 block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white"
            placeholder="********"
          />
        </div>
        <button
          type="submit"
          className="w-full text-white focus:ring-blue-300 font-medium rounder-lg text-sm px-5 py-2.5 text-center bg-brand-orange hover:bg-brand-orange-s"
        >
          Login
        </button>

        <button className="flex w-full justify-end">
          <a href="#" className="text-sm block text-brand-orange hover:underline w-full text-right">
            Forgot Password?
          </a>
        </button>
        <div className="text-sm font-medium text-gray-300">
          Not Registered? {" "}
          <a href="#" className="text-blue-700 hover:underline">
            Create Account
          </a>
        </div>

      </form>
    </div>
  );
}